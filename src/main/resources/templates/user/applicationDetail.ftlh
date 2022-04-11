<#import "../parts/common.ftlh" as c>

<@c.page "Информация об приложении ${application.name}">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Приложение ${application.name}</h1>
            <b>Уникальный индентификатор: ${application.id}</b>
            <#--TODO: to need to add a graph-->
            <div class="form-group">
                <a href="/user/applications">
                    <button class="btn">Перейти ко всем моим приложениям</button>
                </a>
            </div>
        </div>
    </div>
</@c.page>
