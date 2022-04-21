<#import "../parts/common.ftlh" as c>

<@c.page "Информация об приложении ${app.name}">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Приложение ${app.name}</h1>
            <b>Уникальный индентификатор: ${app.id}</b>

            <#--TODO: to need to add a graph-->

            <div class="mb-3 sb-3">
                <a href="/user/applications">
                    <button class="btn btn-primary">Перейти ко всем моим приложениям</button>
                </a>
            </div>
        </div>
    </div>
</@c.page>
