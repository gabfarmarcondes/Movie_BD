package com.movie_bd.repository;

import com.movie_bd.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a.Tipo_Conteudo_Avaliacao, a.Data_Avaliacao, a.Comentario " +
            "FROM Avaliacao a " +
            "WHERE a.Nota >= :notaMaior")
    List<Avaliacao> findNotasMaioresQue(@Param("notaMaior") int Nota);

    List<Avaliacao> findByID_USUARIO_ID_USUARIO(Long idUsuario);

    @Query("SELECT a.ID_USUARIO, a.Nota " +
            "FROM Avaliacao a " +
            "WHERE a.Nota >= :notaMinima O" +
            "RDER BY a.Data_Avaliacao DESC")
    List<Avaliacao> findNotasOrdenadas(@Param("notaMinima") int Nota);

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.Tipo_Conteudo_Avaliacao = :tipoConteudo")
    long countPorTipoDeConteudo(@Param("tipoConteudo") String Tipo_Conteudo_Avaliacao);
}
