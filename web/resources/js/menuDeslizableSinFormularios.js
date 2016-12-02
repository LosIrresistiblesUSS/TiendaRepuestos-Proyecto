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

window.onload = function()
{
    document.getElementById("nav-enlace-mantenimientos").addEventListener( 'click' , cambiaClaseMantenimiento);
    document.getElementById("nav-enlace-reportes").addEventListener( 'click' , cambiaClaseReporte);
    document.getElementById("nav-enlace-operaciones").addEventListener('click', cambiaClaseOperaciones);
};