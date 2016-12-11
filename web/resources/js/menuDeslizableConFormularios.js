function cambiaClaseMantenimiento()
{
    $('#nav-enlace-mantenimientos').toggleClass('active');
    $('#collapseReportes').removeClass('in');
    $('#collapseOperaciones').removeClass('in');
    $('#nav-enlace-reportes').removeClass('active');
    $('#nav-enlace-operaciones').removeClass('active');
}
function cambiaClaseReporte()
{
    $('#nav-enlace-reportes').toggleClass('active');
    $('#collapseMantenimientos').removeClass('in');
    $('#collapseOperaciones').removeClass('in');
    $('#nav-enlace-mantenimientos').removeClass('active');
    $('#nav-enlace-operaciones').removeClass('active');
}
function cambiaClaseOperaciones()
{
    $('#nav-enlace-operaciones').toggleClass('active');
    $('#collapseMantenimientos').removeClass('in');
    $('#collapseReportes').removeClass('in');
    $('#nav-enlace-mantenimientos').removeClass('active');
    $('#nav-enlace-reportes').removeClass('active');
}

function hacerFoco(){
    $('.txtBusqueda').focus();
}

window.onload = function()
{
    document.getElementById("nav-enlace-mantenimientos").addEventListener( 'click' , cambiaClaseMantenimiento);
    document.getElementById("nav-enlace-reportes").addEventListener( 'click' , cambiaClaseReporte);
    document.getElementById("nav-enlace-operaciones").addEventListener('click', cambiaClaseOperaciones);
    
    /*Foco en Los Modal*/
    $('#modalNuevoTipoServicio').on('shown.bs.modal', function () {
        $('.tipoServicio-descripcion').focus();
    });
    $('#modalModificarTipoServicio').on('shown.bs.modal', function () {
        $('.tipoServicio-descripcion').focus();
    });
    
    $('#modalNuevoTipoDocumento').on('shown.bs.modal', function () {
        $('.tipoDocumento-descripcion').focus();
    });
    $('#modalModificarTipoDocumento').on('shown.bs.modal', function () {
        $('.tipoDocumento-descripcion').focus();
    });
    
    $('#modalNuevoTipoEmpleado').on('shown.bs.modal', function () {
        $('.tipoEmpleado-descripcion').focus();
    });
    $('#modalModificarTipoEmpleado').on('shown.bs.modal', function () {
        $('.tipoEmpleado-descripcion').focus();
    });
    
    $('#modalNuevoRepuesto').on('shown.bs.modal', function () {
        $('.repuesto-descripcion').focus();
    });
    $('#modalModificarRepuesto').on('shown.bs.modal', function () {
        $('.repuesto-descripcion').focus();
    });
    
    $('#modalNuevoTipoCliente').on('shown.bs.modal', function () {
        $('.tipoCliente-descripcion').focus();
    });
    
    $('#modalModificarTipoCliente').on('shown.bs.modal', function () {
        $('.tipoCliente-descripcion').focus();
    });
    
    $('#modalCliente').on('shown.bs.modal', function () {
        $('.txtBusqueda').focus();
    });
    
    hacerFoco();
};