
# Workshop: Desenvolvimento de Aplicação para Conectar ONGs e Tutores

## Objetivo da Aplicação
A aplicação tem como objetivo facilitar a conexão entre ONGs e possíveis tutores, com a missão de garantir que todos os animais possam encontrar um lar digno e carinhoso. A plataforma permite a busca e a adoção de animais por meio de uma interface amigável e intuitiva.

## Tecnologias Utilizadas
- **Backend**: Java com Spring Boot
- **Frontend**: HTML, CSS e JavaScript
- **Banco de Dados**: SQL Server (inicialmente) adaptado para MySQL

## Problemas Enfrentados
Durante o desenvolvimento, alguns problemas técnicos foram identificados, especialmente relacionados ao banco de dados MySql. Um dos principais desafios foi a impossibilidade de usar `triggers` para alterar a própria tabela onde estão registradas, obrigando o uso de uma solução alternativa com `UPDATE` ao invés de `DELETE`.

Outro desafio está relacionado à duplicação de e-mails e outros campos no banco de dados, principalmente em casos de desativação de contas, o que deve ser resolvido em futuras atualizações.

## Sugestões de Melhorias
- **Escalabilidade**: Alterar o tipo de dado dos IDs de `Integer` para `Double` para melhorar a escalabilidade do sistema.
- **Segurança**: Implementar mecanismos de verificação de e-mails para evitar duplicações e problemas futuros.

## Funcionalidades Principais
1. **Busca de Animais por Filtros**: A aplicação permite que o usuário busque animais com base no tipo, cidade e estado. Essa funcionalidade facilita o encontro do animal ideal para adoção.
2. **Anotações Spring/Jakarta**: A aplicação utiliza anotações do ecossistema Spring e Jakarta para facilitar o desenvolvimento e a integração com APIs REST.

## Explicação das Anotações

### Jakarta Persistence API (JPA)

1. **`@Entity`**
   - **Origem**: Jakarta Persistence (`jakarta.persistence`)
   - **Descrição**: Indica que a classe é uma entidade e será mapeada para uma tabela no banco de dados.
   - **Exemplo**:
     ```java
     @Entity
     public class Animal { ... }
     ```

2. **`@Id`**
   - **Origem**: Jakarta Persistence (`jakarta.persistence`)
   - **Descrição**: Define o campo como a chave primária da entidade.
   - **Exemplo**:
     ```java
     @Id
     private Integer id;
     ```

3. **`@GeneratedValue`**
   - **Origem**: Jakarta Persistence (`jakarta.persistence`)
   - **Descrição**: Define que o valor da chave primária será gerado automaticamente.
   - **Exemplo**:
     ```java
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     ```

4. **`@OneToOne`**
   - **Origem**: Jakarta Persistence (`jakarta.persistence`)
   - **Descrição**: Representa um relacionamento de "um para um" entre duas entidades.
   - **Exemplo**:
     ```java
     @OneToOne
     private Passaporte passaporte;
     ```

### Spring Data JPA

5. **`@Repository`**
   - **Origem**: Spring (`org.springframework.stereotype`)
   - **Descrição**: Marca a classe como um repositório de acesso a dados.
   - **Exemplo**:
     ```java
     @Repository
     public interface AnimalRepository extends JpaRepository<Animal, Integer> { ... }
     ```

6. **`@Query`**
   - **Origem**: Spring Data JPA (`org.springframework.data.jpa.repository`)
   - **Descrição**: Usada para definir consultas personalizadas em repositórios.
   - **Exemplo**:
     ```java
     @Query("SELECT a FROM Animal a WHERE a.tipo = :tipo")
     List<Animal> findAnimaisByTipo(@Param("tipo") String tipo);
     ```

### Spring Web

7. **`@RestController`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Combina `@Controller` e `@ResponseBody`, criando um controlador REST que retorna dados JSON ou XML.
   - **Exemplo**:
     ```java
     @RestController
     public class AnimalController { ... }
     ```

8. **`@GetMapping`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Mapeia requisições HTTP GET para um método de controlador.
   - **Exemplo**:
     ```java
     @GetMapping("/animais")
     public List<Animal> getAllAnimais() { ... }
     ```

9. **`@PostMapping`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Mapeia requisições HTTP POST para um método de controlador.
   - **Exemplo**:
     ```java
     @PostMapping("/animais")
     public Animal createAnimal(@RequestBody Animal animal) { ... }
     ```

10. **`@PutMapping`**
    - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
    - **Descrição**: Mapeia requisições HTTP PUT para atualizar recursos.
    - **Exemplo**:
      ```java
      @PutMapping("/animais/{id}")
      public Animal updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) { ... }
      ```

11. **`@DeleteMapping`**
    - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
    - **Descrição**: Mapeia requisições HTTP DELETE para excluir recursos.
    - **Exemplo**:
      ```java
      @DeleteMapping("/animais/{id}")
      public void deleteAnimal(@PathVariable Integer id) { ... }
      ```

12. **`@RequestMapping`**
    - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
    - **Descrição**: Define o mapeamento de requisições para uma classe ou método do controlador, especificando a rota e o método HTTP.
    - **Exemplo**:
      ```java
      @RequestMapping("/animais")
      public class AnimalController { ... }
      ```

### Spring Core

13. **`@Service`**
    - **Origem**: Spring (`org.springframework.stereotype`)
    - **Descrição**: Define que a classe é um serviço e contém a lógica de negócios.
    - **Exemplo**:
      ```java
      @Service
      public class AnimalService { ... }
      ```

14. **`@Autowired`**
    - **Origem**: Spring (`org.springframework.beans.factory.annotation`)
    - **Descrição**: Realiza injeção de dependências automaticamente no Spring.
    - **Exemplo**:
      ```java
      @Autowired
      private AnimalRepository animalRepository;
      ```

15. **`@PathVariable`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Usada para vincular um parâmetro de caminho de URL a um parâmetro de método no controlador. Isso é útil quando o valor faz parte da URL, como um identificador único.
   - **Exemplo**:
     ```java
     @GetMapping("/animais/{id}")
     public Animal getAnimalById(@PathVariable Integer id) {
         return animalService.findById(id);
     }
     ```

16. **`@RequestBody`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Liga o corpo da requisição HTTP ao parâmetro de um método no controlador. Normalmente utilizado para receber objetos JSON que são enviados no corpo da requisição.
   - **Exemplo**:
     ```java
     @PostMapping("/animais")
     public Animal createAnimal(@RequestBody Animal animal) {
         return animalService.save(animal);
     }
     ```

17. **`@RequestParam`**
   - **Origem**: Spring MVC (`org.springframework.web.bind.annotation`)
   - **Descrição**: Liga um parâmetro de uma string de consulta (query string) a um parâmetro de método no controlador. Usado para capturar valores de parâmetros que vêm na URL após o símbolo de interrogação `?`.
   - **Exemplo**:
     ```java
     @GetMapping("/animais")
     public List<Animal> getAnimalsByType(@RequestParam String tipo) {
         return animalService.findByTipo(tipo);
     }
     ```