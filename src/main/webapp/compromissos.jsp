<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <h1>Agendamento de Compromissos</h1>
    <form action="CadastroCompromissos" id="formAgendamento" method="POST">
        <div class="input-group">
            <label for="titulo">Título</label>
            <input name="titulo" class="form-control" required="required" size="45"/>
        </div>
        
        <div class="input-group">
            <label for="tipo">Tipo</label>
            <select name="tipo" class="form-control">
                <option value="Reuniao" selected="selected">Reunião</option> 
                <option value="Encontro">Encontro</option>
                <option value="Palestra">Palestra</option>
                <option value="Conferencia">Conferência</option>
                <option value="Outro">Outro</option>
            </select>
        </div>
        <div class="input-group">
            <label for="data">Data</label>
            <input name="data" size="11" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="hora">Hora</label>
            <input name="hora" size="5" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="local">Local</label>
            <input name="local" size="45" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="duracao">Duração</label>
            <input name="duracao" size="13" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="observacao">Observação</label>
            <textarea name="observacao" cols="50" rows="3" required="required" class="form-control"></textarea>
        </div>
            
        <input type="submit" value="Cadastrar">
    </form>
</t:master>