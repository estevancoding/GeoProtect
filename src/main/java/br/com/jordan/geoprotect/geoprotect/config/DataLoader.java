package br.com.jordan.geoprotect.geoprotect.config;

import br.com.jordan.geoprotect.geoprotect.domain.Usuario;
import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.repository.UsuarioRepository;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.n52.jackson.datatype.jts.JtsModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

/**
 * Popula o banco de dados com dados iniciais (zonas de risco e usuários)
 * na inicialização da aplicação, apenas se o banco estiver vazio.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    // Construtor padrão é suficiente, pois estamos usando injeção de campo.
    public DataLoader() {
    }

    @Override
    public void run(String... args) throws Exception {
        loadZonasDeRisco();
        loadAdminUser();
    }

    private void loadZonasDeRisco() throws Exception {
        if (zonaDeRiscoService.listarTodas().isEmpty()) {
            log.info("Carregando zonas de risco iniciais do arquivo geojson...");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JtsModule());

            Resource resource = resourceLoader.getResource("classpath:data/zonas-de-risco.geojson");
            InputStream inputStream = resource.getInputStream();

            JsonNode features = mapper.readTree(inputStream).path("features");
            int count = 0;
            for (JsonNode feature : features) {
                JsonNode geometryNode = feature.path("geometry");
                Geometry geometry = mapper.treeToValue(geometryNode, Geometry.class);

                if (geometry instanceof Polygon) {
                    ZonaDeRisco novaZona = new ZonaDeRisco();
                    novaZona.setNome("Zona de Risco " + UUID.randomUUID().toString().substring(0, 6));
                    novaZona.setArea((Polygon) geometry);
                    zonaDeRiscoService.salvar(novaZona);
                    count++;
                }
            }
            log.info("{} zonas de risco carregadas com sucesso.", count);
        } else {
            log.info("O banco de dados já contém zonas de risco. Nenhuma ação necessária.");
        }
    }

    private void loadAdminUser() {
        if (usuarioRepository.count() == 0) {
            log.info("Criando usuário admin padrão...");
            Usuario adminUser = new Usuario(
                    null,
                    adminEmail,
                    passwordEncoder.encode(adminPassword),
                    "ROLE_ADMIN"
            );
            usuarioRepository.save(adminUser);
            log.info("Usuário '{}' criado com a senha padrão.", adminEmail);
        } else {
            log.info("O banco de dados já contém usuários. Nenhuma ação necessária.");
        }
    }
}
