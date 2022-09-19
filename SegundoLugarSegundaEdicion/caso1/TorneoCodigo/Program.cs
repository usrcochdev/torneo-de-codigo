// See https://aka.ms/new-console-template for more information
double montoInversion = 0;
string plazoDiasString = "";
bool imprimirTabla = true;

double[,] tablaInteres = new double[8, 2] {
    {30, 2 },
    {60, 2.20},
    {90, 2.40},
    {120, 2.60},
    {180, 2.80},
    {240, 3},
    {300, 3.20},
    {360, 3.40}
};


Console.Write("Ingrese el monto de inversion: ");
montoInversion = Convert.ToDouble(Console.ReadLine());

Console.Write("Ingrese el Plazo en dias: ");
plazoDiasString = Console.ReadLine();

if(Int32.TryParse(plazoDiasString, out int plazoDias))
{
    imprimirTabla = false;
    Console.WriteLine(plazoDias);
}


double CalcularInteresGanado(double monto, int plazoDias, double tazaInteres){
    return monto * (tazaInteres/100 * plazoDias / 360);
}

double CalcularTotalRecibir(double monto, double interes)
{
    return monto + interes;
}

double CalcularTazaInteres(int dias)
{
    for (int i = 0; i < tablaInteres.Length; i++)
    {
        if (dias <= tablaInteres[i,0])
        {
            return tablaInteres[0, i];
        }
    }
    return 0;
}

if (imprimirTabla)
{
    double[,] tabla = new double[8, 2];
    for (int i = 0; i < 8; i++)
    {
        tabla[i, 0] = CalcularInteresGanado(montoInversion, Convert.ToInt32(tablaInteres[i, 0]), tablaInteres[i, 1]);
        tabla[i, 1] = CalcularTotalRecibir(montoInversion, tabla[i,0]);

    }
    for (int i = 0; i < 8; i++)
    {
        Console.WriteLine($"{tablaInteres[i, 0]} | {tablaInteres[i, 1]}% | ${tabla[i,0]} | ${tabla[i, 1]}");
    }
}
else
{
    double tazaInteres = CalcularTazaInteres(45);
    double interesGanado = CalcularInteresGanado(montoInversion, plazoDias, tazaInteres);
    double totalRecibir = CalcularTotalRecibir(montoInversion, interesGanado);
    Console.WriteLine($"{plazoDias} | {tazaInteres}% | ${interesGanado} | ${totalRecibir}");

}

Console.ReadKey();