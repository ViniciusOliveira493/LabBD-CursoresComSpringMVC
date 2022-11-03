USE bdCurso
GO
CREATE FUNCTION fn_obterDisciplinasCurso(@codigo INT)
RETURNS @tab TABLE(
    codDisciplina       VARCHAR(8)
    , nomeDisciplina    VARCHAR(70)
    , cargaHorariaDisc  INT
    , nomeCurso         VARCHAR(70)
)
AS 
BEGIN
    DECLARE @nomeCurso VARCHAR(70)
            ,@codDisc  VARCHAR(8)
    SELECT 
        @nomeCurso = nome
    FROM tbCurso
    WHERE codigo = @codigo

    DECLARE disciplinas CURSOR 
        FOR 
        SELECT 
            codigoDisciplina 
        FROM tbDisciplinaCurso
        WHERE codigoCurso = @codigo

    OPEN disciplinas
    FETCH NEXT FROM disciplinas INTO @codDisc
    WHILE @@FETCH_STATUS = 0
    BEGIN
        DECLARE
            @nomeDisc    VARCHAR(70)
            , @cargaHoraDisc  INT

        SELECT
            @nomeDisc = nome 
            , @cargaHoraDisc = cargaHoraria
        FROM tbDisciplina
        WHERE codigo = @codDisc 

        INSERT INTO @tab 
            VALUES
                (@codDisc,@nomeDisc,@cargaHoraDisc,@nomeCurso)
        FETCH NEXT FROM disciplinas INTO @codDisc
    END
    CLOSE disciplinas
    DEALLOCATE disciplinas
    RETURN
END
--======================
SELECT * FROM fn_obterDisciplinasCurso(51)