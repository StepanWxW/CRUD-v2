Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:
User (id, firstName, lastName, List posts, Region region)

Post (id, content, created, updated)

Region (id, name)

Role (enum ADMIN, MODERATOR, USER)

В качестве хранилища данных необходимо использовать JSON файлы:

users.json, posts.json, regions.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

Слои:

model - POJO классы

repository - классы, реализующие доступ к текстовым файлам

controller - обработка запросов от пользователя

view - все данные, необходимые для работы с консолью

Например: User, UserRepository, UserController, UserView и т.д.

Для репозиторного слоя желательно использовать базовый интерфейс:

interface GenericRepository<T,ID>

Interface UserRepository extends GenericRepository<User, Long>

class JsonUserRepositoryImpl implements UserRepository

В рамках данного проекта необходимо активно использовать возможности Stream API и шаблоны проектирования.

Результатом выполнения задания должен быть отдельный репозиторий с рабочим приложением и описанием работы.

Для работы с JSON файлами необходимо использовать библиотеку gson. Для импорта зависимостей - Maven.