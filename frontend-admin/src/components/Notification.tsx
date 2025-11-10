import React, { useEffect } from 'react';
import './Notification.css';

interface NotificationProps {
    message: string;
    onClose: () => void;
}

const Notification: React.FC<NotificationProps> = ({ message, onClose }) => {
    useEffect(() => {
        const timer = setTimeout(() => {
            onClose();
        }, 5000); // A notificação desaparecerá após 5 segundos

        return () => {
            clearTimeout(timer);
        };
    }, [onClose]);

    return (
        <div className="notification">
            <span>{message}</span>
            <button onClick={onClose} className="close-button">&times;</button>
        </div>
    );
};

export default Notification;
