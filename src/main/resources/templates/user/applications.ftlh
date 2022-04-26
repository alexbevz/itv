<#import "../parts/common.ftlh" as c>

<@c.page "Список моих приложений">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">

            <h1>Мои приложения</h1>

            <#if getAppError??>
                <div class="alert alert-danger">
                    ${getAppError}
                </div>
            </#if>

            <ol class="list-group">
                <#list apps as app>
                    <div class="mb-3 sb-3">
                        <li class="list-group-item ">
                            <a href="/user/applications/${app.id?c}">
                                Приложение ${app.name}
                            </a>
                        </li>
                    </div>
                <#else>
                    <strong class="mb-3 sb-3">Нет добавленных приложений!</strong>
                </#list>
            </ol>
            <div class="form-group mb-3 sb-3">
                <a href="/user/applications/add">
                    <button class="btn btn-primary">Добавить новое приложение</button>
                </a>
            </div>
        </div>
    </div>
</@c.page>