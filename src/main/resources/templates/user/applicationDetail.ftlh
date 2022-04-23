<#import "../parts/common.ftlh" as c>

<@c.page "Информация об приложении ${app.name}">
    <div class="row">
        <div class="col">
            <h2>Приложение: ${app.name}</h2>
            <h3>Уникальный индентификатор: ${app.id}</h3>

            <div class="mb-3 sb-3 row flex-nowrap">
                <div class="col-8">
                    <form method="get">
                        <input name="timeFrom" type="date" value="${timeFrom}"/>
                        -
                        <input name="timeTo" type="date" value="${timeTo}"/>
                        <button class="btn btn-primary" type="submit">Отобразить</button>
                        <#if timeError??>
                            <span class="alert alert-danger p-2">${timeError}</span>
                        </#if>
                    </form>
                </div>
                <div class="col-3 btn-group d-flex justify-content-end" role="group">
                    <a class="btn btn-primary" href="/user/applications">Мои приложения</a>
                    <a class="btn btn-success" href="/csv/${app.filenameCsv}">Импорт</a>
                </div>
            </div>

            <div class="mb-3 sb-3">
                <img src="/img/${app.filenameChart}" alt="${app.filenameChart}"/>
            </div>
        </div>
    </div>
</@c.page>
