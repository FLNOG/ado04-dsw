package br.com.grades.model;

public class CursoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String duracao;
    private String professor;
    private String categoria;
    private Double preco;

    public CursoDTO() {}

    public CursoDTO(Long id, String nome, String descricao, String duracao,
                    String professor, String categoria, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.professor = professor;
        this.categoria = categoria;
        this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }

    public String getProfessor() { return professor; }
    public void setProfessor(String professor) { this.professor = professor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}