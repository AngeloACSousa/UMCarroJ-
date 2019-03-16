import java.time.LocalDate;

public abstract class Pessoa {
    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate nascimento;

    public Pessoa(){
        this.nome = "";
        this.email = "";
        this.password = "";
        this.morada = "";
        this.nascimento = null;
    }

    public Pessoa(Pessoa p){
        this.nome = p.getNome();
        this.email = p.getEmail();
        this.password = p.getPassword();
        this.morada = p.getMorada();
        this.nascimento = p.getNascimento();
    }

    public Pessoa(String email, String nome, String pass, String morada, LocalDate nascimento){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pessoa clone(){
        return new Pessoa(this);
    }

    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Pessoa aux = (Pessoa) o;
        return this.equals(aux);
    }
}
