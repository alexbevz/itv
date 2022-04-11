<#import "../parts/common.ftlh" as c>

<@c.page "Список моих приложений">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">

            <h1>Мои приложения</h1>

            <ol class="list-group">
                <#list applications as app>
                    <li class="list-group-item">
                        <a href="/user/applications/${app.id}">
                            Приложение ${app.name}
                        </a>
                    </li>
                    <#else>
                        <strong>Нет добавленных приложений!</strong>
                </#list>
            </ol>
            <div class="form-group">
                <a href="/user/applications/add">
                    <button class="btn">Добавить новое приложение</button>
                </a>
            </div>
        </div>
    </div>
</@c.page>