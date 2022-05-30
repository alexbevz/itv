<#import "parts/common.ftlh" as c>

<@c.page "Страница для отправления событий">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Страница для отправления событий</h1>
            <q>Добавлено специально для тестирования</q>
            <h2>Форма для создания и отправки события</h2>
            <#if success??>
                <div class="alert alert-success">${success}</div>
            </#if>
            <form method="post" action="/event">
                <div class="mb-3 sb-3">
                    <label class="form-label">Название события:</label>
                    <input class="form-control <#if nameError??>is-invalid</#if>"
                           name="name"
                           placeholder="Название события"
                           type="text"
                           value="<#if event??>${event.name}</#if>"/>
                    <#if nameError??>
                        <div class="invalid-feedback">${nameError}</div>
                    </#if>
                </div>
                <div class="mb-3 sb-3">
                    <label class="form-label">Уникальный идентификатор приложения:</label>
                    <input class="form-control <#if applicationError??>is-invalid</#if>"
                           name="appId"
                           placeholder="Уникальный идентификатор приложения"
                           type="text"
                           value="<#if appId??>${appId}</#if>"/>
                    <#if applicationError??>
                        <div class="invalid-feedback">${applicationError}</div>
                    </#if>
                </div>
                <div class="mb-3 sb-3">
                    <label class="form-label">Дополнительная информация:</label>
                    <input class="form-control <#if descriptionError??>is-invalid</#if>"
                           name="description"
                           placeholder="Дополнительная информация"
                           type="text"
                           value="<#if event??>${event.description}</#if>"/>
                    <#if descriptionError??>
                        <div class="invalid-feedback">${descriptionError}</div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="mb-3 sb-3">
                    <button class="btn btn-primary" type="submit">Отправить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>