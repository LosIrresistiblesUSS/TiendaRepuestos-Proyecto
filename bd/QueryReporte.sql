SELECT
     detalleoperacion.`cantidad` AS cantidad,
     detalleoperacion.`precio` AS precio,
     detalleoperacion.`subTotal` AS subTotal

FROM
     `comprobanteventa` comprobanteventa INNER JOIN `detalleventa` detalleventa ON comprobanteventa.`idComprobanteVenta` = detalleventa.`idComprobanteVenta`
     INNER JOIN `detalleoperacion` detalleoperacion ON detalleventa.`idDetalleOperacion` = detalleoperacion.`idDetalleOperacion`
     INNER JOIN `producto` producto ON detalleoperacion.`idProducto` = producto.`idProducto`
WHERE
     comprobanteventa.numero = $P{numero}