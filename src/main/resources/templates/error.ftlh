<#import "parts/common.ftlh" as c>

<@c.page "Непредвиденная ошибка">
    <div class="row">
        <div class="col">
            <h1>Ошибка</h1>
            <#if infoError??>
                <h4>Возникла ошибка, воспользуйтесь меню, чтобы перейти на другую вкладку.</h4>
                <a class="btn btn-info"
                   data-bs-toggle="collapse"
                   href="#collapseExample"
                   role="button"
                   aria-expanded="false"
                   aria-controls="collapseExample">
                    Подробнее об ошибке
                </a>
                <div class="collapse pt-3" id="collapseExample">
                    <div class="card card-body">
                        ${infoError}
                    </div>
                </div>
            <#else>
                <p class="mb-3 sb-3">
                    <meta http-equiv="refresh" content="3;/"/>
                    Произошло что-то веселое!<br/>
                    Переадресация на главную страницу будет произведена через 3 секунд!
                </p>
            </#if>
        </div>
    </div>
</@c.page>