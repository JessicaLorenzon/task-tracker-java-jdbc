CREATE TABLE tarefa (
    id INT PRIMARY KEY AUTO_INCREMENT,
    conteudo TEXT NOT NULL,
    status ENUM('pendente', 'em_progresso', 'concluida') NOT NULL DEFAULT 'pendente',
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO tarefa (conteudo, status) VALUES 
('Finalizar relatório mensal', 'pendente'),
('Revisar código do projeto', 'em_progresso'),
('Enviar e-mail para cliente', 'concluida');