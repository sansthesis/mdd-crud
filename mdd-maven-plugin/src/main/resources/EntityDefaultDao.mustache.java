package {{package}};

{{#imports}}
import {{import}};
{{/imports}}

public class {{entityClassName}}DefaultDao extends DefaultDao<{{entityClassName}}> implements {{entityClassName}}Dao {
}
