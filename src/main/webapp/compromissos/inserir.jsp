<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:master>
    <h1>Agendamento de Compromissos</h1>
    <form action="/compromissos/inserir" id="formAgendamento" method="POST">
        <div class="input-group">
            <label for="titulo">Título</label>
            <input name="titulo" type="text" class="form-control" required="required" size="45"/>
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
            <input name="data" type="text" size="16" required="required" class="form-control" placeholder="07/05/2017 02:00" />
        </div>
        <div class="input-group">
            <label for="local">Local</label>
            <input name="local" type="text" size="45" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="duracao">Duração</label>
            <input name="duracao" type="number" required="required" class="form-control" />
        </div>
        <div class="input-group">
            <label for="observacao">Observação</label>
            <textarea name="observacao" cols="50" rows="3" required="required" class="form-control"></textarea>
        </div>
            
        <div class="input-group">
            <input type="submit" value="Cadastrar">
        </div>
    </form>
</t:master>