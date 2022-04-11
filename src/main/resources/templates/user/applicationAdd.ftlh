<#import "../parts/common.ftlh" as c>

<@c.page "Страница добавления приложения">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Форма добавления приложения</h1>

            <form method="post" action="/user/applications">
                <div class="form-group">
                    <label>
                        Уникальный индентификатор:
                        <input class="form-control"
                               readonly
                               name="id"
                               value="${application.id}"/>
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        Название приложения:
                        <input class="form-control" name="name" value=""/>
                    </label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="form-group">
                    <button class="btn" type="submit">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>