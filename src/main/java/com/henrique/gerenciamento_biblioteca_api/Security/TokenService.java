package com.henrique.gerenciamento_biblioteca_api.Security;

import com.henrique.gerenciamento_biblioteca_api.Model.UserModel;

public interface TokenService {
    String generateToken(UserModel userModel);

    String validateToken(String token);
}
