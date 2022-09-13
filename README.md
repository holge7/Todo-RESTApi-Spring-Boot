# ToDo RESTapi Spring-boot

Api created for the management of an application of tasks connected to a database.



## API Reference

#### Get one task

```http
  GET /task/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required**. Id of task to fetch |

#### Get all tasks

```http
  GET /task/all
```


#### Get Tasks in pageable format
```http
  GET /task/pageable
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `indexPage` | `int` | **Required**. index page |
| `sizePage` | `int` | **Required**. number task for page |
| `sortDirection` | `String` | sort by id 'asc' or 'desc' (default = 'asc') |


#### Get tasks filter by status end
```http
  GET /task/end
```

#### Get tasks filter by status active
```http
  GET /task/active
```

#### Create new Task
```http
  POST /task/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `task` | `TaskDTO` | **Required**. TaskDTO |

#### Delete Task
```http
  DELETE /task/delete/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required**. id |


#### Finalize task
```http
  PUT /task/end
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required**. id |

#### Reactive task
```http
  PUT /task/end
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required**. id |


