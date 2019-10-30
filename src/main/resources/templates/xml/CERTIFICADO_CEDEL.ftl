<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Certificado CeDEL</title>
    <style>
      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
        font-family: sans-serif;
      }

      .contenedor {
        margin-right: auto;
        margin-left: auto;
        border: 40px solid rgb(227,205,62);
        text-align: center;
      }

      .header {
        width: 100%;
        display: flex;
        justify-content: space-between;
      }

      .logo-ccd {
        margin-top: 10px;
        margin-left: 10px;
      }

      .logo-ba {
        margin-top: 35px;
        margin-left: -15px;
      }

      .logo-direccion {
        margin-top: 10px;
        margin-right: 10px;
      }

      h1 {
        font-size: 14px;
        font-weight: 400;
      }

      .ministerio {
        font-size: 14px;
        font-weight: 600;
        margin-bottom: 30px;
      }

      .contenedor-texto {
        width: 650px;
        text-align: center;
        margin-right: auto;
        margin-left: auto;
      }

      .texto {
        font-size: 20px;
        font-weight: 100;
        line-height: 42px;
      }

      .nombre-estudiante {
        font-weight: 600;
        font-size: 24px;
        margin-bottom: 5px;
      }

      .texto-secundario {
        font-size: 20px;
        font-weight: 100;
        line-height: 28px;
        margin-bottom: 40px;
      }

      .footer {
        width: 650px;
        margin: auto;
        display: flex;
        justify-content: space-between;
      }

      .nombre {
        font-size: 14px;
        font-weight: 600;
      }

      .cargo {
        font-size: 14px;
      }
    </style>
  </head>
  <body>
    <table class="contenedor" style="width: 100%;height: 100%">
      <tr class="header">
            <td align="left" style="width: 30%;height: 10%;">
              <div class="firma-izquierda">
                <img class="img-firma" src="${path}bfa.png" style="width: 169px;height: 115px;" />
              </div>
            </td>
            <td align="center" style="width: 40%; height: 10%;padding-top: 15px;">   
                <div class="logo-ba">
                  <img  class="logo" src="${path}logo_ba.png"style="width: 47px;height: 51px;" alt="Logo del gobierno de la ciudad de Buenos Aires"  />
                  <h1>Gobierno de la Ciudad de Buenos Aires</h1>
                  <p class="ministerio">Secretaría de Integración Social y Urbana</p>
                </div>
            </td>
            <td align="right" style="width: 30%;height: 10%;">   
                <div class="logo-direccion">
                  <img src="${path}cedel_icon.png" alt="Logo de la Centro de desarrollo Emprendedor y Laboral" height="75px" width="150px" />
                </div>
            </td>
      </tr>
      <tr>
          <td style="width: 100%;padding: 30px;" colspan="3">  
              <div>
            	<p style="font-size: 20px;font-weight: 100;line-height: 42px;font-family: sans-serif;">Se certifica que ${titulo_cedel.nombre} , DNI ${titulo_cedel.dni} ha finalizado satisfactoriamente  
            	</p>
            	<p style="font-size: 20px;font-weight: 100;line-height: 42px;font-family: sans-serif;">el curso de
            	${titulo_cedel.curso} con una carga horaria de ${titulo_cedel.duracion} horas. en el CeDEL.</p>
   				<p><br/></p>
            	<p><br/></p>
            	<p>Ciudad de Buenos Aires, ${fecha_emision}.</p>
              </div>
          </td>          
      </tr>
      <tr>          
           <td>

             </td>
          <td>
          </td>             
           <td align="center" style="width: 40%; height: 10%;padding-top: 15px;"> 
              <div class="firma-derecha">
              <img class="img-firma" src="${path}firma_tomas_galmarini.png" style="width: 279px;height: 195px;float: left;"/>
              </div>

           </td>
      </tr>
    </table>
  </body>
</html>
