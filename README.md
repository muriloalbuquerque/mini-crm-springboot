📇 Mini CRM com Spring Boot
https://img.shields.io/badge/Java-17-orange.svg
https://img.shields.io/badge/Spring%2520Boot-3.x-green.svg
https://img.shields.io/badge/Maven-3.8%252B-blue.svg
https://img.shields.io/badge/H2-Database-lightgrey.svg
https://img.shields.io/badge/License-MIT-yellow.svg

📋 Descrição
O Mini CRM com Spring Boot é um sistema de gerenciamento de relacionamento com clientes (CRM) simplificado, desenvolvido como uma aplicação web RESTful. Ele permite o cadastro de clientes e seus respectivos contatos, oferecendo operações básicas de CRUD através de uma API intuitiva.

Este projeto foi desenvolvido como demonstração das capacidades do Spring Boot para criação de APIs REST eficientes e bem estruturadas.

🛠️ Tecnologias Utilizadas
Java 17

Spring Boot 3.x

Spring Web

Spring Data JPA

Spring Validation

Hibernate como implementação JPA

H2 Database (banco em memória)

Lombok para redução de boilerplate code

Maven como ferramenta de build

HATEOAS para hypermedia

⚙️ Funcionalidades
✅ Criar novos clientes

✅ Listar todos os clientes cadastrados

✅ Adicionar contatos a clientes existentes

✅ Listar contatos de um cliente específico

✅ Validação de dados de entrada

✅ Interface H2 Console para visualização do banco de dados

🗄️ Modelagem de Dados
Entidade Cliente
java
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Email(message = "Email deve ser válido")
    private String email;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();
}
Entidade Contato
java
@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tipo é obrigatório")
    private String tipo; // Ex: Telefone, Email, WhatsApp
    
    @NotBlank(message = "Valor é obrigatório")
    private String valor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
Relacionamento
Cliente → Contato: One-to-Many (Um cliente pode ter vários contatos)

Contato → Cliente: Many-to-One (Muitos contatos pertencem a um cliente)

🚀 Como Executar o Projeto
Pré-requisitos
Java 17 ou superior

Maven 3.8 ou superior

Git

Passos para execução
Clone o repositório

bash
git clone https://github.com/seu-usuario/mini-crm-springboot.git
cd mini-crm-springboot
Execute a aplicação com Maven

bash
./mvnw spring-boot:run
Acesse a aplicação

API REST: http://localhost:8080

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (deixe em branco)

📡 Endpoints da API
Criar um cliente
http
POST /clientes
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@email.com"
}
Listar todos os clientes
http
GET /clientes
Accept: application/json
Adicionar contato a um cliente
http
POST /clientes/{id}/contatos
Content-Type: application/json

{
  "tipo": "Telefone",
  "valor": "(11) 99999-9999"
}
Listar contatos de um cliente
http
GET /clientes/{id}/contatos
Accept: application/json
Exemplo de uso com curl
bash
# Criar cliente
curl -X POST http://localhost:8080/clientes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos","email":"maria@email.com"}'

# Listar clientes
curl -X GET http://localhost:8080/clientes

# Adicionar contato
curl -X POST http://localhost:8080/clientes/1/contatos \
  -H "Content-Type: application/json" \
  -d '{"tipo":"Email","valor":"contato@maria.com"}'

# Listar contatos do cliente
curl -X GET http://localhost:8080/clientes/1/contatos
📚 Aprendizados
Este projeto proporcionou a prática e o entendimento de:

Desenvolvimento de APIs RESTful com Spring Boot

Configuração e uso do Spring Data JPA com Hibernate

Implementação de relacionamentos entre entidades (One-to-Many)

Uso de banco de dados em memória H2 para desenvolvimento

Redução de código boilerplate com Lombok

Validação de dados com Bean Validation

Implementação de HATEOAS para APIs hipermedia-driven

Estruturação adequada de projetos Spring Boot

Configuração e uso do Maven como ferramenta de build

Desenvolvido com ❤️ usando Spring Boot
