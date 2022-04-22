<#import "../parts/common.ftlh" as c>

<@c.page "Информация об приложении ${app.name}">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2>Приложение: ${app.name}</h2>
            <h3>Уникальный индентификатор: ${app.id}</h3>

            <form method="get">
                <input class="" name="timeFrom" type="date" />
                 -
                <input class="" name="timeTo" type="date" />
                <button class="btn btn-primary" type="submit">Отобразить</button>
            </form>
            <div class="sb-3 mb-3">
                <img src="/img/${app.chart}">
            </div>

            <div class="mb-3 sb-3">
                <a href="/user/applications">
                    <button class="btn btn-primary">Перейти ко всем моим приложениям</button>
                </a>
            </div>
        </div>
    </div>
</@c.page>
