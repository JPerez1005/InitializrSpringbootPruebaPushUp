![](https://raw.githubusercontent.com/CampusLands/DER/main/DER.jpg)

**1. Listar todas las ventas que se realizaron en el mes de julio de 2023**

```sql
select * from venta where month(fecha)=7 and year(fecha)=2023;
```

**2. Seleccionar todos los empleados con sus respectivos cargos y municipios**

```sql
select e.nombre as empleado, c.Descripcion as cargo, m.nombre as municipio
from empleado e
inner join cargos c on e.cargos_id=c.id
inner join municipio m on e.municipio_id=m.id;
```

**3. Obtener la lista de todas las ventas con la información de los clientes y la forma de pago**

```sql
select v.*, c.nombre as cliente,fp.descripcion as forma_pago
from venta v
inner join cliente c on v.cliente_id=c.id
inner join forma_pago fp on v.formaPago_id=fp.id;
```

**4. Mostrar los detalles de todas las órdenes junto con los nombres de los empleados y clientes asociados**

```sql
select o.*,e.nombre as empleado,c.nombre as cliente
from orden o
inner join empleado e on e.empleado_id=e.id
inner join cliente c on o.cliente_id = c.id;
```

**5. Listar los productos disponibles en el inventario junto con su talla y color**

```sql
select p.*, t.descripcion as talla,c.descripcion as color
from inventario i
inner join prenda p on i.prenda_id=p.id
inner join talla t on i.talla_id=t.id
inner join color c on p.color_id=c.id;
```

**6. Mostrar todos los proveedores junto con la lista de insumos que suminis**

```sql
select pr.*, i.nombre as insumo
from proveedor pr
inner join insumo_proveedor ip on pr.id=ip.proveedor_id
inner join insumo i on ip.insumo_id=i.id;
```

**7. Encontrar la cantidad de ventas realizadas por cada empleado**

```sql
select e.nombre as empleado, count(v.id) as cantidad_ventas
from empleado e
left join venta v on e.id=v.empleado_id
group by e.id;
```

**8. Mostrar la lista de órdenes en proceso junto con los nombres de los clientes y empleados asociados**

```sql
select o.*,e.nombre as empleado,c.nombre as cliente
from orden o
inner join empleado e on o.empleado_id=e.id
inner join cliente c on o.cliente_id = c.id;
```

**9. Obtener el nombre de la empresa y su respectivo representante legal junto con el nombre del municipio al que pertenecen**

```sql
select emp.razon_social as empresa, emp.representante_legal, mun.nombre as municipio
from empresa emp
inner join municipio mun on emp.municipio_id=mun.id;
```

**10. Mostrar la lista de prendas y su respectivo stock disponible**

```sql
select p.*, sum(it.cantidad) as stock_disponible
from prenda p
left join inventario i on p.id=i.prenda_id
left join inventario_talla it on i.id= it.inventario_id
group by p.id;
```

**11. Encontrar el nombre de los clientes que realizaron compras en una fecha específica junto con la cantidad de artículos comprados**

```sql
select c.nombre as cliente, count(dv.id) as cantidad_comprada
from venta v
inner join cliente c on v.cliente_id = c.id
inner join detalle_venta dv on v.id=dv.venta_id
where DATE(v.fecha) = '2023-07-15'
group by c.id;
```

**12. Mostrar la lista de empleados y la duración de su empleo en años **

```sql
select nombre as empleado, TIMESTAMPDIFF(YEAR,fecha_ingreso,CURDATE()) as años_empleo
from empleado;
```

**13. Obtener el nombre de las prendas junto con el valor total de ventas en dólares para cada una **

```sql
select p.nombre as prenda, sum(dv.cantidad*dv.valor_unit) as valor_total_usd
from detalle_venta dv
inner join invenatrio i on dv.inventario_id=i.id
inner join prenda p on i.prenda_id=p.id
group by p.id;
```

**14. Obtener el nombre de las prendas junto con la cantidad mínima y máxima de insumos necesarios para su fabricación **

```sql
select p.nombre as prenda, min(ip.cantidad) as cantidad_minima, max(ip.cantidad) as cantidad_maxima
from insumo_prendas ip
inner join prenda p on ip.prenda_id=p.id
group by p.id;
```

**15. Obtener la lista de empleados y su información de contacto, incluyendo el nombre, el cargo y el municipio **

```sql
select e.nombre as empleado, e.telefono,e.email,c.descripcion as cargo,m.nombre as municipio
from empleado e
inner join cargos c on e.cargos_id=c.id
inner join municipio m on e.municipio_id=m.id;
```

**16. Mostrar la lista de ventas realizadas en un rango de fechas específico junto con el nombre del cliente y la forma de pago **

```sql
select v.id as venta_id, v.fecha, c.nombre as cliente, fp.descripcion as forma_pago
from venta v
inner join cliente c on v.cliente_id=c.id
inner join forma_pago fp on v.forma_pago_id=fp.id
where v.fecha
between '2023-07-01' and '2023-07-31';
```

**17. Obtener el nombre de las prendas y su valor unitario en dólares junto con el estado de disponibilidad **

```sql
select p.nombre as prenda, p.descripcion as descripcion_prenda, p.valor_unit_usd, e.descripcion as estado_disponibilidad
from prenda p
inner join estado e on p.estado_id=e.id; 
```

**18. Mostrar la lista de clientes y la cantidad de compras que han realizado **

```sql
select c.nombre as cliente, count(v.id) as cantidad_compras
from cliente c
left join venta v on c.id=v.cliente_id
group by c.id;
```

**19. Obtener la lista de órdenes junto con el estado actual y la fecha en que se crearon **

```sql
select o.id as orden_id, e.descripcion as estado_actual, o.fecha
from orden o
inner join estado e on o.estado_id=e.id;
```

**20. Obtener el nombre y la descripción de los cargos con un sueldo base superior a 2.000.000  **

```sql
select descripcion as cargo, sueldo_base
from cargos
where sueldo_base>2000000;
```

**21. Mostrar la lista de clientes con sus respectivos municipios y países  **

```sql
select c.nombre as cliente, m.nombre as municipio, p.nombre as pais
from cliente c
inner join municipio m on c.municipio_id=m.id
inner join departamento d on m.departamento_id=d.id
inner join pais p on d.pais_id=p.id;
```

**22 Obtener el nombre y la descripción de los tipos de protección y el número de prendas asociadas a cada tipo  **

```sql
select tp.descripcion as tipo_proteccion, count(p.id) as cantidad_prendas
from tipo_proteccion tp
left join prenda p on  tp.id=p.tipo_proteccion_id
group by tp.id;
```

**23 Mostrar la lista de empleados con sus cargos y fechas de ingreso ordenados por la fecha de ingreso de manera descendente  **

```sql
select e.nombre as empleado, c.descripcion as cargo, e.fecha_ingreso
from empleado e
inner join cargos c on e.cargos_id=c.id
order by e.fecha_ingreso desc;
```

**24 Mostrar el nombre y la descripción de todos los cargos junto con la cantidad de empleados en cada cargo  **

```sql
select c.descripcion as cargo, count(e.id) as cantidad_empleados
from cargos c
left join empleado e on c.id=e.cargos_id
group by c.id;
```

**25 Obtener el nombre y la descripción de los estados junto con la cantidad de prendas asociadas a cada estado  **

```sql
select e.descripcion as estado, count(p.id) as cantidad_prendas
from estado e
left join prenda p on e.id=p.estado_id
group by e.id;
```

**26 Obtener el nombre y la descripción de los tipos de persona junto con la cantidad de clientes asociados a cada tipo  **

```sql
select tp.descripcion as tipo_pago, count(v.id) as csasntidad_ventas
from tipo_pago tp
left join venta v on tp.id=v.tipo_pago_id
group by tp.id;
```

**27 Mostrar el nombre y la descripción de los tipos de protección junto con la cantidad de prendas asociadas a cada tipo  **

```sql
select i.descripcion as tipo_insumo, count(ip.prenda_id) as cantidad_prendas
from insumo i
left join insumo_prendas ip on i.id=ip.insumo_id
group by i.id;
```

**28 Obtener el nombre y la descripción de los estados junto con la cantidad de órdenes asociadas a cada estado  **

```sql
select c.nombre as cliente, sum(dv.cantidad*dv.valor_unit) as total_gastado
from cliente c
left join venta v on c.id=v.cliente_id
left join detalle_venta dv on v.id=dv.venta_id
group by c.id;
```

**29 Obtener el nombre y la descripción de los tipos de pago junto con la cantidad de ventas asociadas a cada tipo  **

```sql
select p.nombre as prenda, sum(it.stock) as stock_disponible
from prenda p
left join inventario i on p.id=i.prenda_id
left join inventario_talla it on i.id=it.inventario_id
group by p.id;
```

**30 Mostrar el nombre y la descripción de los tipos de insumos junto con la cantidad de prendas que los utilizan **

```sql
select p.nombre as prenda, p.descripcion as descripcion_prenda, sum(dv.cantidad * dv.valor_unit) as valor_total_cop
from prenda p
left join inventario i on p.id=i.prenda_id
left join detalle_venta dv on i.id=dv.inventario_id
group by p.id;
```

**31 Obtener el nombre de los clientes y la cantidad total gastada por cada uno en ventas **

```sql
select e.descripcion as estado, count(p.id) as cantidad_prendas
from estado e
left join prenda p on e.id=p.estado_id
group by e.id
order by cantidad_prendas asc;
```

**32 Mostrar el nombre y la descripción de las prendas junto con el valor total de ventas en pesos colombianos para cada una **

```sql
select tp.nombre as tipo_persona, count(c.id) as cantidad_clientes
from tipo_persona tp
left join cliente c on tp.id=c.tipo_persona_id
group by tp.id;
```

**33 Mostrar el nombre y la descripción de los estados junto con la cantidad de prendas asociadas a cada estado en orden ascendente de la cantidad de prendas **

```sql
select tp.descripcion as tipo_proteccion , count(p.id) as cantidad_prendas
from tipo_proteccion tp
left join prenda p on tp.id=p.tipo_proteccion_id
group by tp.id;
```