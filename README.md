# Sobre o projeto

#### O projeto foi desenvolvido em Java com base no framework Spring Boot.


## Requisítos para execução do projeto
#### Observação: as configurações para execução do projeto podem ser alteradas no arquivo _application.properties_
~~~
    # Servidor WEB
    server.port=${port:8085}
    
    # Bando de dados
    spring.h2.console.enabled=true
    spring.datasource.url=jdbc:h2:mem:texo
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.show-sql=false
    spring.jpa.properties.hibernate.format_sql=true
    spring.datasource.driverClassName=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
    
    # JPA
    spring.jpa.open-in-view=false
    
    # File
    spring.servlet.multipart.max-file-size=2MB
    spring.servlet.multipart.max-request-size=2MB
~~~

- A porta do servidor é 8085
- Banco de dados H2 com usuário _sa_ e sem senha
- Java JDK 11
- IDE IntelliJ IDEA, Spring Tool Suite ou Eclipse

## Configuração da IDE

- O Projeto está configurado para utilização do [Lombok][lombok]. Caso use a IDE Eclipse ou STS, para configurá-la clique em [Lombok][lombok].

## Subindo o projeto
- Fazer o clone do projeto no [Github][github] .
- Para os testes, executar a classe (MovieControllerTest) do pacote de testes (com.pifrans.texo.controllers) 
- Para usar a aplicação, executar a classe (ProvaTexoApplication) do pacote (com.pifrans.texo)
- Esse [arquivo](src/main/resources/static/Texo.postman_collection.json) contém os endpoints criados e podem ser importados para uso no Postman


[lombok]: https://projectlombok.org/setup/eclipse
[github]: https://github.com/pifrans/prova-texo
