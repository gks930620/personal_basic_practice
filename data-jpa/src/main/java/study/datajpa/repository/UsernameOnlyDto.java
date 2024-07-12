package study.datajpa.repository;

public class UsernameOnlyDto {

    private final String username;

    public UsernameOnlyDto(String username){
        this.username = username;
    }
    public String getusername(){
        return username;
    }


}
