# 🧩 Архитектура базы данных
### На этом этапе выполнено проектирование базы данных и создание основных сущностей проекта. Ниже описана структура и логика построения модели данных.

## 🗄️ Используемая СУБД: PostgreSQL 

## 🧱 Сущности проекта
#### User
![Схема базы данных](./photosForDoc/userEntity.png)

#### Follow и embedded FollowId
![Схема базы данных](./photosForDoc/followEntity.png) ![Схема базы данных](./photosForDoc/EmbeddableFollowId.png)

#### Like и embedded LikeId
![Схема базы данных](./photosForDoc/likeEntity.png) ![Схема базы данных](./photosForDoc/EmbeddableLikeId.png)

#### Tweet
![Схема базы данных](./photosForDoc/tweetEntity.png)

#### Comment
![Схема базы данных](./photosForDoc/commentEntity.png)

#### Role
![Схема базы данных](./photosForDoc/userRole.png)

## 📷 Схема базы данных
![Схема базы данных](./photosForDoc/schemaDatabase.png)
