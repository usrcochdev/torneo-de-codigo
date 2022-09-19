double valorPrestamo = 0;
double tazaInteresAnual = 0;
int plazoAnios = 0;
int PagosPorAnio = 0;

Console.WriteLine("--------- PRESTAMOS BANCO PICHINCHA ---------");

//entrada del usuario
while (valorPrestamo <= 0)
{
    Console.WriteLine("Ingrese valor del prestamo:");
    Console.Write("$");
    try
    {
        valorPrestamo = Convert.ToDouble(Console.ReadLine());
        if (valorPrestamo <= 0)
        {
            Console.WriteLine("El valor debe ser mayor a cero");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("El valor debe ser numerico");
    }
}

while (plazoAnios < 1)
{
    Console.WriteLine("Ingrese plazo en años:");
    try
    {
        plazoAnios = Convert.ToInt32(Console.ReadLine());
        if (plazoAnios < 1)
        {
            Console.WriteLine("El valor debe ser mayor o igual a 1");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("El valor debe ser numerico");

    }
}

while (tazaInteresAnual <= 0)
{
    Console.WriteLine("Ingrese la taza de interes anual:");
    try
    {
        tazaInteresAnual = Convert.ToDouble(Console.ReadLine());
        if (tazaInteresAnual <= 0)
        {
            Console.WriteLine("El valor debe ser mayor a 0");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("El valor debe ser numerico");
    }
}

while (PagosPorAnio < 12)
{
    Console.WriteLine("Ingrese numero de pagos por año:");
    try
    {
        PagosPorAnio = Convert.ToInt32(Console.ReadLine());
        if (PagosPorAnio < 12)
        {
            Console.WriteLine("El valor debe ser mayor o igual a 12");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("El valor debe ser numerico");
    }
}

//funciones para calculos
double CalcularInteresPeriodico(double tazaInteresAnual, int anios)
{
    return tazaInteresAnual / anios;
}

int CalcularNumeroCuotas(int anios, int numeroPagoPorAnios)
{
    return anios * numeroPagoPorAnios;
}

double CalcularInteresPeriodo(double saldo, double interesPeriodico){
    return Math.Round( saldo * interesPeriodico/100, 2 );
}

double CalcularCapitalAmortizado(double saldo, double totalPrestamo, int numeroCuotas)
{
    if (saldo < 0.05)
    {
        return 0;
    }
    return Math.Round(totalPrestamo / numeroCuotas, 2);
}

double CalcularCuotaPagar(double interesPeriodo, double capitalAmortizado)
{
    return Math.Round(interesPeriodo + capitalAmortizado, 2);
}

double CalcularSaldoRestante(double saldo, double capitalAmortizado)
{
    return Math.Round(saldo - capitalAmortizado, 2);
}

int cuotas = CalcularNumeroCuotas(plazoAnios, PagosPorAnio);
double interesPeriodico = CalcularInteresPeriodico(tazaInteresAnual, PagosPorAnio);
double[,] tablaResultado = new double[cuotas, 5];

//Impresion de resultados
Console.WriteLine("--------------- RESULTADOS ---------------");
Console.WriteLine("N° de cuota  | Intereses del periodo | Capital Amoritzado | Cuota a pagar | Saldo Remantente");
tablaResultado[0, 0] = 1;
tablaResultado[0, 1] = CalcularInteresPeriodo(valorPrestamo, interesPeriodico);
tablaResultado[0, 2] = CalcularCapitalAmortizado(valorPrestamo, valorPrestamo, cuotas);
tablaResultado[0, 3] = CalcularCuotaPagar(tablaResultado[0,1], tablaResultado[0, 2]);
tablaResultado[0, 4] = valorPrestamo;
for (int i = 1; i < cuotas; i++)
{
    tablaResultado[i, 4] = CalcularSaldoRestante(tablaResultado[i-1, 4], tablaResultado[i-1, 2]);
    tablaResultado[i, 2] = CalcularCapitalAmortizado(tablaResultado[i, 4], valorPrestamo, cuotas);
    tablaResultado[i, 1] = CalcularInteresPeriodo(tablaResultado[i, 4], interesPeriodico);
    tablaResultado[i, 0] = i+1;
    tablaResultado[i, 3] = CalcularCuotaPagar(tablaResultado[i, 1], tablaResultado[i, 2]);
}

for (int i = 0; i < cuotas; i++)
{
    Console.WriteLine($"{tablaResultado[i, 0]} | ${tablaResultado[i, 1]} | ${tablaResultado[i, 2]} | ${tablaResultado[i, 3]} | ${tablaResultado[i, 4]}");
}