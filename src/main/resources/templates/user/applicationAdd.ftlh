<#import "../parts/common.ftlh" as c>

<@c.page "Страница добавления приложения">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Форма добавления приложения</h1>
            <form method="post" action="/user/applications">
                <div class="mb-3 sb-3">
                    <label class="form-label">Уникальный индентификатор:</label>
                    <input class="form-control"
                           name="id"
                           readonly
                           value="${app.id}"/>
                </div>
                <div class="mb-3 sb-3">
                    <label class="form-label">Название приложения:</label>
                    <input class="form-control" name="name"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="mb-3 sb-3">
                    <button class="btn btn-primary" type="submit">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>