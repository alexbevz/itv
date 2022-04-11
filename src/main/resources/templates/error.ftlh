<#import "parts/common.ftlh" as c>

<@c.page "Непредвиденная ошибка">
    <meta http-equiv="refresh" content="5;/" />
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Ошибка</h1>
            <div>
                ${error}
            </div>
            <b>Вас автоматически перенаправит на главную страницу через 5 секунд!</b>
        </div>
    </div>
</@c.page>