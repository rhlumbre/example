package com.examplejava.example.Controllers;

import com.examplejava.example.Dao.UsuarioDao;
import com.examplejava.example.Dao.UsuarioDaoImp;
import com.examplejava.example.Models.Usuario;
import com.examplejava.example.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Rbn");
        usuario.setApellido("Hdl");
        usuario.setEmail("rbnHdl@gmail.com");
        //usuario.setTelefono("666777888");
        return usuario;

    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        if(!validarToken(token)){return null;}
        String usuarioId = jwtUtil.getKey(token);
        if(usuarioId == null){
            return new ArrayList<>();
        }
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }


    @RequestMapping(value = "usuario123")
    public Usuario editar(){

        return new Usuario();
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id){
        if(!validarToken(token)){return ;}
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "usuario12345")
    public Usuario buscar(){

        return new Usuario();
    }

    @RequestMapping(value = "usuarios1111")
    public Usuario crearlo(){

        return new Usuario();
    }


}
