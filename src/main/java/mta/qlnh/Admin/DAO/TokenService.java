package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Token;
import mta.qlnh.Admin.Repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class TokenService {
    @Autowired
    private TokenRepository repo;
    public void save(Token token) {
        repo.save(token);
    }
    public boolean getToken(String token){

        Optional<Token> tk =  repo.findByToken(token);
        tk.ifPresent(item ->{
            item.setRevoked(true);
        });
        return tk.isPresent();
    }

    public int getUserId(String token){
        Optional<Token> tk =  repo.findByToken(token);
        if(tk.isPresent()){
            return tk.get().user.getId();
        }
        return 0;
    }
}
