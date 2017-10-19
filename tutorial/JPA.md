# JPA

> #### Anote a EntidadeRais com @MappedSupeclass

```java
@MappedSuperclass
public abstract class EntidadeRaiz implements Serializable {
...
```


> #### Anote o getId com @Id e @GeneratedValue

```java
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return this.id;
    }
```


> #### Anote a classe Jogo com @Entity , @Table(name="jogos") e @Named, conforme abaixo:

```java
   	@Entity
	@Table(name = "jogos")
	@Named(key = "Jogo")
	public class Jogo extends EntidadeRaiz {
	...
```

> #### Coloque as anotações de relacionamento na entidade Jogo

```java
@Column(name = "nome_do_jogo", unique = true)
public String getNome() {
...
```

```java
@ManyToMany
public List<Plataforma> getPlataformas() {
...
```

```java
@ManyToOne
public Desenvolvedora getDesenvolvedora() {
...
```

```java
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
public List<Imagem> getImagens() {
...
```

```java
@OrderBy("nome")
@ManyToMany
public List<Plataforma> getPlataformas() {
...
```

```java
@Transient
public Float getPrecoComDesconto() {
...
```

```java
@Lob
public byte[] getBytes() {
...
```

> #### Coloque na Desenvolvedora

```java
@OneToMany(mappedBy = "desenvolvedora")
@OrderBy("nome")
public List<Jogo> getJogos() {
...
```

> #### Entre nas demais entidades e descomente as anotações de relacionamento.


## Entity Manager

O Entity Manager é o gerenciador das entidades: remove, cria, edita e atualiza (CRUD)A configuração dele é feita no persistence.xml.

> #### Abra o persistence.xml para ver como é.

## Transação

Uma transação é uma operação atomica.

```java
this.em.getTransaction().begin();
...
this.em.getTransaction().commit();
```

## Service

Services guardam as regras de negócio da aplicação. São a camada de Controle do modelo MVC.Em geral, criamos interfaces para reduzir o acoplamento entre os módulos.

```java
public interface DesenvolvedoraService {

    void criar(Desenvolvedora desenvolvedora);

    void remover(Desenvolvedora desenvolvedora);

    void editar(Desenvolvedora desenvolvedora);

    Desenvolvedora recuperar(Long id);

    List<Desenvolvedora> buscarTodos();
}
```


> #### Implementar os metodos do serviço da DesenvolvedoraServiceImpl

```java
@Override
public void criar(Desenvolvedora desenvolvedora) {
    EntityManager em = EMF.createEntityManager();

    em.getTransaction().begin();
    em.persist(desenvolvedora);
    em.getTransaction().commit();
}
```

```java
@Override
public void remover(Desenvolvedora desenvolvedora) {
    EntityManager em = EMF.createEntityManager();

    Desenvolvedora connectedEntity = em.find(Desenvolvedora.class, desenvolvedora.getId());
    if (connectedEntity == null) {
        return;
    }

    em.getTransaction().begin();
    em.remove(connectedEntity);
    em.getTransaction().commit();
}
```

```java
@Override
public void editar(Desenvolvedora desenvolvedora) {
    EntityManager em = EMF.createEntityManager();

    em.getTransaction().begin();
    em.merge(desenvolvedora);
    em.getTransaction().commit();
}
```

```java
@Override
public Desenvolvedora recuperar(Long id) {
    // Não é necessário transação. Nunca?

    EntityManager em = EMF.createEntityManager();

    Desenvolvedora desenvolvedora = em.find(Desenvolvedora.class, id);
    return desenvolvedora;
}
```

```java
@Override
public List<Desenvolvedora> buscarTodos() {
    EntityManager em = EMF.createEntityManager();

    String queryString = "SELECT d FROM Desenvolvedora d ORDER BY d.nome";
    Query query = em.createQuery(queryString);
    List<Desenvolvedora> desenvolvedoras = QueryTyper.getResultList(query);
    return desenvolvedoras;
}
```


> #### Implementar os metodos de busca no `JogoServiceImpl`

```java
@Override
public List<Jogo> buscar(Plataforma plataforma) {
    EntityManager em = EMF.createEntityManager();

    String queryString = "SELECT j FROM Jogo j WHERE :plataforma MEMBER OF j.plataformas ORDER BY j.nome";
    Query query = em.createQuery(queryString);

    Plataforma plataformaConectada = em.find(Plataforma.class, plataforma.getId());
    query.setParameter("plataforma", plataformaConectada);

    em.getTransaction().begin();
    List<Jogo> jogos = QueryTyper.getResultList(query);
    em.getTransaction().commit();

    return jogos;
}
```

```java
@Override
public List<Jogo> buscar(String nome) {
    EntityManager em = EMF.createEntityManager();

    String queryString = "SELECT j FROM Jogo j WHERE UPPER( j.nome) LIKE UPPER(:nome) ORDER BY j.nome";
    Query query = em.createQuery(queryString);
    String nomebusca = nome == null ? "" : nome;
    query.setParameter("nome", "%" + nomebusca + "%");

    em.getTransaction().begin();
    List<Jogo> jogos = QueryTyper.getResultList(query);
    em.getTransaction().commit();

    return jogos;
}
```
