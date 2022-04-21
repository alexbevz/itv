<#import "parts/common.ftlh" as c>

<@c.page "Страница для отправления событий">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h1>Страница для отправления событий</h1>
            <q>Добавленно специально для тестирования</q>
            <h2>Форма для создания и отправки события</h2>
            <form method="post">
                <div class="mb-3 sb-3">
                    <label class="form-label">Название события:</label>
                    <input class="form-control"
                           name="name"
                           placeholder="Название события"
                           type="text"/>
                </div>
                <div class="mb-3 sb-3">
                    <label class="form-label">Дополнительная информация:</label>
                    <input class="form-control"
                           name="description"
                           placeholder="Дополнительная информация"
                           type="text"/>
                </div>
                <div class="mb-3 sb-3">
                    <label class="form-label">Уникальный индентификатор приложения:</label>
                    <input class="form-control"
                           name="application"
                           placeholder="Уникальный индентификатор приложения"
                           type="text"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="mb-3 sb-3">
                    <button class="btn btn-primary" type="submit">Отправить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>