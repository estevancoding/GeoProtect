package br.com.jordan.geoprotect.geoprotect.config;

import br.com.jordan.geoprotect.geoprotect.domain.Caminho;
import br.com.jordan.geoprotect.geoprotect.domain.ZonaDeRisco;
import br.com.jordan.geoprotect.geoprotect.repository.CaminhoRepository;
import br.com.jordan.geoprotect.geoprotect.service.ZonaDeRiscoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Polygon;
import org.n52.jackson.datatype.jts.JtsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @Autowired
    private CaminhoRepository caminhoRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule());

        loadZonasDeRisco(mapper);
        loadCaminho(mapper);
    }

    private void loadZonasDeRisco(ObjectMapper mapper) throws Exception {
        if (zonaDeRiscoService.listarTodas().isEmpty()) {
            System.out.println(">>> Banco de dados vazio. Carregando zonas de risco do arquivo GeoJSON...");

            Resource resource = resourceLoader.getResource("classpath:data/zonas-de-risco.geojson");
            InputStream inputStream = resource.getInputStream();

            JsonNode rootNode = mapper.readTree(inputStream);
            JsonNode features = rootNode.path("features");

            int count = 0;
            for (JsonNode feature : features) {
                JsonNode geometryNode = feature.path("geometry");
                Geometry geometry = mapper.treeToValue(geometryNode, Geometry.class);

                if (geometry instanceof Polygon) {
                    Polygon polygon = (Polygon) geometry;

                    ZonaDeRisco novaZona = new ZonaDeRisco();
                    novaZona.setNome("Zona de Risco " + UUID.randomUUID().toString().substring(0, 6));
                    novaZona.setArea(polygon);

                    zonaDeRiscoService.salvar(novaZona);
                    count++;
                }
            }
            System.out.println(">>> " + count + " zonas de risco carregadas com sucesso!");
        } else {
            System.out.println(">>> Zonas de risco já carregadas. Carregamento ignorado.");
        }
    }

    private void loadCaminho(ObjectMapper mapper) throws Exception {
        if (caminhoRepository.count() == 0) {
            System.out.println(">>> Carregando caminho de simulação do arquivo GeoJSON...");

            Resource resource = resourceLoader.getResource("classpath:data/caminho.geojson");
            InputStream inputStream = resource.getInputStream();

            JsonNode rootNode = mapper.readTree(inputStream);
            JsonNode features = rootNode.path("features");

            if (features.has(0)) {
                JsonNode geometryNode = features.get(0).path("geometry");
                Geometry geometry = mapper.treeToValue(geometryNode, Geometry.class);

                if (geometry instanceof LineString) {
                    LineString lineString = (LineString) geometry;
                    Caminho novoCaminho = new Caminho();
                    novoCaminho.setNome("Rota de Simulação 1");
                    novoCaminho.setTrajeto(lineString);
                    caminhoRepository.save(novoCaminho);
                    System.out.println(">>> Caminho de simulação carregado com sucesso!");
                }
            }
        } else {
            System.out.println(">>> Caminho de simulação já carregado. Carregamento ignorado.");
        }
    }
}