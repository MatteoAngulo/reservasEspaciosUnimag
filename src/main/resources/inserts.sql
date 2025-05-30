INSERT INTO rol (rol_enum) VALUES
('ADMINISTRADOR'),('ESTUDIANTE');

INSERT INTO sedes (name) VALUES
('Sede Principal'),('Sede Secundaria');

INSERT INTO usuario (correo, contrasena, rol_id) VALUES
('juan.perez@unimagdalena.edu.co','P3r3zMag@1',1),
('maria.rodriguez@unimagdalena.edu.co','R0dr1guez!2',2),
('carlos.gomez@unimagdalena.edu.co','G0mez#345',1),
('ana.martinez@unimagdalena.edu.co','M4rt1nez$4',2),
('diego.lopez@unimagdalena.edu.co','L0pez%567',1),
('laura.sanchez@unimagdalena.edu.co','S@nchez&6',2),
('andres.torres@unimagdalena.edu.co','T0rr3s*78',1),
('natalia.flores@unimagdalena.edu.co','Fl0res(89)',2),
('jose.ramirez@unimagdalena.edu.co','R@mirez)90',1),
('daniela.cruz@unimagdalena.edu.co','CrUz_1010',2),
('miguel.herrera@unimagdalena.edu.co','H3rr3ra+11',1),
('paula.moreno@unimagdalena.edu.co','Mor3no-12',2),
('fernando.castillo@unimagdalena.edu.co','C@st1llo=13',1),
('andrea.jimenez@unimagdalena.edu.co','J1mén3z!14',2),
('ricardo.vega@unimagdalena.edu.co','V3ga@15',1),
('claudia.rios@unimagdalena.edu.co','R10s%16',2),
('sebastian.delgado@unimagdalena.edu.co','D3lgadO&17',1),
('valentina.castro@unimagdalena.edu.co','C@str0*18',2),
('alejandro.silva@unimagdalena.edu.co','S1lv@_19',1),
('carolina.ortiz@unimagdalena.edu.co','0rt1z!20',2);

INSERT INTO estudiante (codigo_estudiantil, nombre, usuario_id) VALUES
('2020101001','Juan Perez',1),
('2020202002','Maria Rodriguez',2),
('2021103003','Carlos Gomez',3),
('2021204004','Ana Martinez',4),
('2022105005','Diego Lopez',5),
('2022206006','Laura Sanchez',6),
('2023107007','Andres Torres',7),
('2023208008','Natalia Flores',8),
('2024109009','Jose Ramirez',9),
('2024210010','Daniela Cruz',10),
('2020111011','Miguel Herrera',11),
('2020212012','Paula Moreno',12),
('2021113013','Fernando Castillo',13),
('2021214014','Andrea Jimenez',14),
('2022115015','Ricardo Vega',15),
('2022201016','Claudia Rios',16),
('2023112017','Sebastian Delgado',17),
('2023203018','Valentina Castro',18),
('2024104019','Alejandro Silva',19),
('2024205020','Carolina Ortiz',20);

INSERT INTO espacios (nombre, tipo, restricciones, disponible, id_sede) VALUES
('Cancha Principal','CANCHAS','Ninguna', TRUE, 1),
('Auditorio Central','AUDITORIOS','Silencio', FALSE, 2),
('Cubículo 101','CUBÍCULOS','Reservar con 24h de antelación', TRUE, 1),
('Salón de Conferencias','SALONES','Horario limitado', TRUE, 1),
('Zona de Estudio Libre','ZONAS_COMUNES','Ninguna', FALSE, 1),
('Sala Audiovisual A','SALONES_AUDIVISUALES','Equipo audiovisual', TRUE, 1),
('Cancha de Fútbol 5','CANCHAS','Prohibido comida', TRUE, 1),
('Auditorio Menor','AUDITORIOS','Silencio', FALSE, 2),
('Cubículo 202','CUBÍCULOS','Reservar con 48h de antelación', TRUE, 1),
('Salón Multiusos','SALONES','Horario limitado', TRUE, 1),
('Zona de Relax','ZONAS_COMUNES','Ninguna', FALSE, 1),
('Sala de Proyección B','SALONES_AUDIVISUALES','Micrófono disponible', TRUE, 1),
('Cancha de Baloncesto','CANCHAS','Uso con calzado adecuado', TRUE, 1),
('Gran Auditorio','AUDITORIOS','Silencio', FALSE, 1),
('Cubículo 303','CUBÍCULOS','Reservar con 72h de antelación', TRUE, 1),
('Salón de Clases 1','SALONES','Horario limitado', TRUE, 1),
('Área de Descanso','ZONAS_COMUNES','Ninguna', FALSE, 1),
('Sala de Ensayo','SALONES_AUDIVISUALES','Instrumentos disponibles', TRUE, 1),
('Cancha de Vóley','CANCHAS','Prohibido comida', TRUE, 1),
('Auditorio Experimental','AUDITORIOS','Silencio', FALSE, 2);

INSERT INTO horario_espacio (id_espacio, dia, hora_inicio, hora_fin) VALUES
-- Espacio 1
(1, 'MONDAY',    '06:00:00', '08:00:00'),
(1, 'TUESDAY',   '08:00:00', '10:00:00'),
(1, 'WEDNESDAY', '10:00:00', '12:00:00'),
(1, 'THURSDAY',  '12:00:00', '14:00:00'),
(1, 'FRIDAY',    '14:00:00', '16:00:00'),
(1, 'SATURDAY',  '16:00:00', '18:00:00'),
(1, 'SUNDAY',    '18:00:00', '20:00:00'),

-- Espacio 2
(2, 'MONDAY',    '08:00:00', '10:00:00'),
(2, 'TUESDAY',   '10:00:00', '12:00:00'),
(2, 'WEDNESDAY', '12:00:00', '14:00:00'),
(2, 'THURSDAY',  '14:00:00', '16:00:00'),
(2, 'FRIDAY',    '16:00:00', '18:00:00'),
(2, 'SATURDAY',  '18:00:00', '20:00:00'),
(2, 'SUNDAY',    '06:00:00', '08:00:00'),

-- Espacio 3
(3, 'MONDAY',    '10:00:00', '12:00:00'),
(3, 'TUESDAY',   '12:00:00', '14:00:00'),
(3, 'WEDNESDAY', '14:00:00', '16:00:00'),
(3, 'THURSDAY',  '16:00:00', '18:00:00'),
(3, 'FRIDAY',    '18:00:00', '20:00:00'),
(3, 'SATURDAY',  '06:00:00', '08:00:00'),
(3, 'SUNDAY',    '08:00:00', '10:00:00'),

-- Espacio 4
(4, 'MONDAY',    '12:00:00', '14:00:00'),
(4, 'TUESDAY',   '14:00:00', '16:00:00'),
(4, 'WEDNESDAY', '16:00:00', '18:00:00'),
(4, 'THURSDAY',  '18:00:00', '20:00:00'),
(4, 'FRIDAY',    '06:00:00', '08:00:00'),
(4, 'SATURDAY',  '08:00:00', '10:00:00'),
(4, 'SUNDAY',    '10:00:00', '12:00:00'),

-- Espacio 5
(5, 'MONDAY',    '14:00:00', '16:00:00'),
(5, 'TUESDAY',   '16:00:00', '18:00:00'),
(5, 'WEDNESDAY', '18:00:00', '20:00:00'),
(5, 'THURSDAY',  '06:00:00', '08:00:00'),
(5, 'FRIDAY',    '08:00:00', '10:00:00'),
(5, 'SATURDAY',  '10:00:00', '12:00:00'),
(5, 'SUNDAY',    '12:00:00', '14:00:00'),

-- Espacio 6
(6, 'MONDAY',    '16:00:00', '18:00:00'),
(6, 'TUESDAY',   '18:00:00', '20:00:00'),
(6, 'WEDNESDAY', '06:00:00', '08:00:00'),
(6, 'THURSDAY',  '08:00:00', '10:00:00'),
(6, 'FRIDAY',    '10:00:00', '12:00:00'),
(6, 'SATURDAY',  '12:00:00', '14:00:00'),
(6, 'SUNDAY',    '14:00:00', '16:00:00'),

-- Espacio 7
(7, 'MONDAY',    '18:00:00', '20:00:00'),
(7, 'TUESDAY',   '06:00:00', '08:00:00'),
(7, 'WEDNESDAY', '08:00:00', '10:00:00'),
(7, 'THURSDAY',  '10:00:00', '12:00:00'),
(7, 'FRIDAY',    '12:00:00', '14:00:00'),
(7, 'SATURDAY',  '14:00:00', '16:00:00'),
(7, 'SUNDAY',    '16:00:00', '18:00:00'),

-- Espacio 8
(8, 'MONDAY',    '06:00:00', '08:00:00'),
(8, 'TUESDAY',   '08:00:00', '10:00:00'),
(8, 'WEDNESDAY', '10:00:00', '12:00:00'),
(8, 'THURSDAY',  '12:00:00', '14:00:00'),
(8, 'FRIDAY',    '14:00:00', '16:00:00'),
(8, 'SATURDAY',  '16:00:00', '18:00:00'),
(8, 'SUNDAY',    '18:00:00', '20:00:00'),

-- Espacio 9
(9, 'MONDAY',    '08:00:00', '10:00:00'),
(9, 'TUESDAY',   '10:00:00', '12:00:00'),
(9, 'WEDNESDAY', '12:00:00', '14:00:00'),
(9, 'THURSDAY',  '14:00:00', '16:00:00'),
(9, 'FRIDAY',    '16:00:00', '18:00:00'),
(9, 'SATURDAY',  '18:00:00', '20:00:00'),
(9, 'SUNDAY',    '06:00:00', '08:00:00'),

-- Espacio 10
(10,'MONDAY',    '10:00:00', '12:00:00'),
(10,'TUESDAY',   '12:00:00', '14:00:00'),
(10,'WEDNESDAY', '14:00:00', '16:00:00'),
(10,'THURSDAY',  '16:00:00', '18:00:00'),
(10,'FRIDAY',    '18:00:00', '20:00:00'),
(10,'SATURDAY',  '06:00:00', '08:00:00'),
(10,'SUNDAY',    '08:00:00', '10:00:00'),

-- Espacio 11
(11,'MONDAY',    '12:00:00', '14:00:00'),
(11,'TUESDAY',   '14:00:00', '16:00:00'),
(11,'WEDNESDAY', '16:00:00', '18:00:00'),
(11,'THURSDAY',  '18:00:00', '20:00:00'),
(11,'FRIDAY',    '06:00:00', '08:00:00'),
(11,'SATURDAY',  '08:00:00', '10:00:00'),
(11,'SUNDAY',    '10:00:00', '12:00:00'),

-- Espacio 12
(12,'MONDAY',    '14:00:00', '16:00:00'),
(12,'TUESDAY',   '16:00:00', '18:00:00'),
(12,'WEDNESDAY', '18:00:00', '20:00:00'),
(12,'THURSDAY',  '06:00:00', '08:00:00'),
(12,'FRIDAY',    '08:00:00', '10:00:00'),
(12,'SATURDAY',  '10:00:00', '12:00:00'),
(12,'SUNDAY',    '12:00:00', '14:00:00'),

-- Espacio 13
(13,'MONDAY',    '16:00:00', '18:00:00'),
(13,'TUESDAY',   '18:00:00', '20:00:00'),
(13,'WEDNESDAY', '06:00:00', '08:00:00'),
(13,'THURSDAY',  '08:00:00', '10:00:00'),
(13,'FRIDAY',    '10:00:00', '12:00:00'),
(13,'SATURDAY',  '12:00:00', '14:00:00'),
(13,'SUNDAY',    '14:00:00', '16:00:00'),

-- Espacio 14
(14,'MONDAY',    '18:00:00', '20:00:00'),
(14,'TUESDAY',   '06:00:00', '08:00:00'),
(14,'WEDNESDAY', '08:00:00', '10:00:00'),
(14,'THURSDAY',  '10:00:00', '12:00:00'),
(14,'FRIDAY',    '12:00:00', '14:00:00'),
(14,'SATURDAY',  '14:00:00', '16:00:00'),
(14,'SUNDAY',    '16:00:00', '18:00:00'),

-- Espacio 15
(15,'MONDAY',    '06:00:00', '08:00:00'),
(15,'TUESDAY',   '08:00:00', '10:00:00'),
(15,'WEDNESDAY', '10:00:00', '12:00:00'),
(15,'THURSDAY',  '12:00:00', '14:00:00'),
(15,'FRIDAY',    '14:00:00', '16:00:00'),
(15,'SATURDAY',  '16:00:00', '18:00:00'),
(15,'SUNDAY',    '18:00:00', '20:00:00'),

-- Espacio 16
(16,'MONDAY',    '08:00:00', '10:00:00'),
(16,'TUESDAY',   '10:00:00', '12:00:00'),
(16,'WEDNESDAY', '12:00:00', '14:00:00'),
(16,'THURSDAY',  '14:00:00', '16:00:00'),
(16,'FRIDAY',    '16:00:00', '18:00:00'),
(16,'SATURDAY',  '18:00:00', '20:00:00'),
(16,'SUNDAY',    '06:00:00', '08:00:00'),

-- Espacio 17
(17,'MONDAY',    '10:00:00', '12:00:00'),
(17,'TUESDAY',   '12:00:00', '14:00:00'),
(17,'WEDNESDAY', '14:00:00', '16:00:00'),
(17,'THURSDAY',  '16:00:00', '18:00:00'),
(17,'FRIDAY',    '18:00:00', '20:00:00'),
(17,'SATURDAY',  '06:00:00', '08:00:00'),
(17,'SUNDAY',    '08:00:00', '10:00:00'),

-- Espacio 18
(18,'MONDAY',    '12:00:00', '14:00:00'),
(18,'TUESDAY',   '14:00:00', '16:00:00'),
(18,'WEDNESDAY', '16:00:00', '18:00:00'),
(18,'THURSDAY',  '18:00:00', '20:00:00'),
(18,'FRIDAY',    '06:00:00', '08:00:00'),
(18,'SATURDAY',  '08:00:00', '10:00:00'),
(18,'SUNDAY',    '10:00:00', '12:00:00'),

-- Espacio 19
(19,'MONDAY',    '14:00:00', '16:00:00'),
(19,'TUESDAY',   '16:00:00', '18:00:00'),
(19,'WEDNESDAY', '18:00:00', '20:00:00'),
(19,'THURSDAY',  '06:00:00', '08:00:00'),
(19,'FRIDAY',    '08:00:00', '10:00:00'),
(19,'SATURDAY',  '10:00:00', '12:00:00'),
(19,'SUNDAY',    '12:00:00', '14:00:00'),

-- Espacio 20
(20,'MONDAY',    '16:00:00', '18:00:00'),
(20,'TUESDAY',   '18:00:00', '20:00:00'),
(20,'WEDNESDAY', '06:00:00', '08:00:00'),
(20,'THURSDAY',  '08:00:00', '10:00:00'),
(20,'FRIDAY',    '10:00:00', '12:00:00'),
(20,'SATURDAY',  '12:00:00', '14:00:00'),
(20,'SUNDAY',    '14:00:00', '16:00:00');


INSERT INTO problema (id_espacio, estado, descripcion, fecha, id_estudiante) VALUES
(1, 'PENDIENTE'    , 'Red de marcadores no funciona en Cancha Principal'           , '2025-05-01', 1),
(2, 'EN_REVISION'  , 'El proyector no enciende en Auditorio Central'               , '2025-05-02', 2),
(3, 'EN_PROCESO'   , 'Falta de ventilación en Cubículo 101'                       , '2025-05-03', 3),
(4, 'RESUELTO'     , 'Silla dañada en Salón de Conferencias'                      , '2025-05-04', 4),
(5, 'CERRADO'      , 'Lámpara fundida en Zona de Estudio Libre'                   , '2025-05-05', 5),
(6, 'PENDIENTE'    , 'Problemas con altavoces en Sala Audiovisual A'              , '2025-05-06', 6),
(7, 'EN_REVISION'  , 'Redes de seguridad flojas en Cancha de Fútbol 5'            , '2025-05-07', 7),
(8, 'EN_PROCESO'   , 'Micrófono con interferencia en Auditorio Menor'             , '2025-05-08', 8),
(9, 'RESUELTO'     , 'Puerta atascada en Cubículo 202'                            , '2025-05-09', 9),
(10,'CERRADO'      , 'Proyector fuera de foco en Salón Multiusos'                 , '2025-05-10',10),
(11,'PENDIENTE'    , 'Sillones desgastados en Zona de Relax'                      , '2025-05-11',11),
(12,'EN_REVISION'  , 'Pantalla agrietada en Sala de Proyección B'                 , '2025-05-12',12),
(13,'EN_PROCESO'   , 'Tableros de puntuación apagados en Cancha de Baloncesto'    , '2025-05-13',13),
(14,'RESUELTO'     , 'Problemas de acústica en Gran Auditorio'                   , '2025-05-14',14),
(15,'CERRADO'      , 'Iluminación deficiente en Cubículo 303'                     , '2025-05-15',15),
(16,'PENDIENTE'    , 'Proyector parpadea en Salón de Clases 1'                    , '2025-05-16',16),
(17,'EN_REVISION'  , 'Mobiliario roto en Área de Descanso'                        , '2025-05-17',17),
(18,'EN_PROCESO'   , 'Fallo en sistema de grabación en Sala de Ensayo'            , '2025-05-18',18),
(19,'RESUELTO'     , 'Red anti–resbalante suelta en Cancha de Vóley'              , '2025-05-19',19),
(20,'CERRADO'      , 'Equipo de sonido desconectado en Auditorio Experimental'    , '2025-05-20',20);


INSERT INTO reserva (id_estudiante, id_horario_espacio, estado_reserva, fecha, motivo) VALUES
(1,  1,  'PENDIENTE', '2025-06-01', 'Práctica deportiva en Cancha Principal el Lunes de 06:00 a 08:00'),
(2,  2,  'APROBADO',  '2025-06-02', 'Sesión de entrenamiento en Cancha Principal el Martes de 08:00 a 10:00'),
(3,  3,  'RECHAZADO', '2025-06-03', 'Uso recreativo en Cancha Principal el Miércoles de 10:00 a 12:00'),
(4,  4,  'CANCELADA', '2025-06-04', 'Competencia interna en Cancha Principal el Jueves de 12:00 a 14:00'),
(5,  5,  'PENDIENTE', '2025-06-05', 'Torneo amistoso en Cancha Principal el Viernes de 14:00 a 16:00'),
(6,  6,  'APROBADO',  '2025-06-06', 'Entrenamiento grupal en Cancha Principal el Sábado de 16:00 a 18:00'),
(7,  7,  'RECHAZADO', '2025-06-07', 'Clínica deportiva en Cancha Principal el Domingo de 18:00 a 20:00'),
(8,  8,  'CANCELADA', '2025-06-08', 'Charla académica en Auditorio Central el Lunes de 08:00 a 10:00'),
(9,  9,  'PENDIENTE', '2025-06-09', 'Presentación audiovisual en Auditorio Central el Martes de 10:00 a 12:00'),
(10, 10, 'APROBADO',  '2025-06-10', 'Simposio en Auditorio Central el Miércoles de 12:00 a 14:00'),
(11, 11, 'RECHAZADO', '2025-06-11', 'Seminario en Auditorio Central el Jueves de 14:00 a 16:00'),
(12, 12, 'CANCELADA', '2025-06-12', 'Conferencia en Auditorio Central el Viernes de 16:00 a 18:00'),
(13, 13, 'PENDIENTE', '2025-06-13', 'Proyección especial en Auditorio Central el Sábado de 18:00 a 20:00'),
(14, 14, 'APROBADO',  '2025-06-14', 'Ciclo de cine en Auditorio Central el Domingo de 06:00 a 08:00'),
(15, 15, 'RECHAZADO', '2025-06-15', 'Reunión de estudio en Cubículo 101 el Lunes de 10:00 a 12:00'),
(16, 16, 'CANCELADA','2025-06-16','Examen oral en Cubículo 101 el Martes de 12:00 a 14:00'),
(17, 17, 'PENDIENTE','2025-06-17','Taller de discusión en Cubículo 101 el Miércoles de 14:00 a 16:00'),
(18, 18, 'APROBADO', '2025-06-18','Sesión de tutoría en Cubículo 101 el Jueves de 16:00 a 18:00'),
(19, 19, 'RECHAZADO','2025-06-19','Trabajo grupal en Cubículo 101 el Viernes de 18:00 a 20:00'),
(20, 20, 'CANCELADA','2025-06-20','Consulta académica en Cubículo 101 el Sábado de 06:00 a 08:00');


INSERT INTO historial_reserva (id_reserva, estado_reserva, fecha_cambio, comentario) VALUES
-- Reserva 1 (PENDIENTE sólo)
(1, 'PENDIENTE', '2025-06-01 06:00:00', 'Reserva creada, esperando aprobación'),

-- Reserva 2 (PENDIENTE → APROBADO)
(2, 'PENDIENTE', '2025-06-02 08:00:00', 'Reserva creada, esperando aprobación'),
(2, 'APROBADO',  '2025-06-02 09:00:00', 'Reserva aprobada por administración'),

-- Reserva 3 (PENDIENTE → RECHAZADO)
(3, 'PENDIENTE', '2025-06-03 10:00:00', 'Reserva creada, esperando aprobación'),
(3, 'RECHAZADO', '2025-06-03 11:00:00', 'Reserva rechazada por conflicto de horario'),

-- Reserva 4 (PENDIENTE → CANCELADA)
(4, 'PENDIENTE', '2025-06-04 12:00:00', 'Reserva creada, esperando aprobación'),
(4, 'CANCELADA','2025-06-04 13:00:00', 'Reserva cancelada por el usuario'),

-- Reserva 5 (PENDIENTE sólo)
(5, 'PENDIENTE', '2025-06-05 14:00:00', 'Reserva creada, esperando aprobación'),

-- Reserva 6 (PENDIENTE → APROBADO)
(6, 'PENDIENTE', '2025-06-06 16:00:00', 'Reserva creada, esperando aprobación'),
(6, 'APROBADO',  '2025-06-06 17:00:00', 'Reserva aprobada por administración'),

-- Reserva 7 (PENDIENTE → RECHAZADO)
(7, 'PENDIENTE', '2025-06-07 18:00:00', 'Reserva creada, esperando aprobación'),
(7, 'RECHAZADO', '2025-06-07 19:00:00', 'Reserva rechazada por uso no autorizado'),

-- Reserva 8 (PENDIENTE → CANCELADA)
(8,  'PENDIENTE', '2025-06-08 08:00:00', 'Reserva creada, esperando aprobación'),
(8,  'CANCELADA','2025-06-08 09:00:00', 'Reserva cancelada por el usuario'),

-- Reserva 9 (PENDIENTE sólo)
(9,  'PENDIENTE', '2025-06-09 10:00:00', 'Reserva creada, esperando aprobación'),

-- Reserva 10 (PENDIENTE → APROBADO)
(10, 'PENDIENTE', '2025-06-10 12:00:00', 'Reserva creada, esperando aprobación'),
(10, 'APROBADO',  '2025-06-10 13:00:00', 'Reserva aprobada por administración'),

-- Reserva 11 (PENDIENTE → RECHAZADO)
(11, 'PENDIENTE', '2025-06-11 14:00:00', 'Reserva creada, esperando aprobación'),
(11, 'RECHAZADO', '2025-06-11 15:00:00', 'Reserva rechazada por cruce de eventos'),

-- Reserva 12 (PENDIENTE → CANCELADA)
(12, 'PENDIENTE', '2025-06-12 16:00:00', 'Reserva creada, esperando aprobación'),
(12, 'CANCELADA','2025-06-12 17:00:00', 'Reserva cancelada por el usuario'),

-- Reserva 13 (PENDIENTE sólo)
(13, 'PENDIENTE', '2025-06-13 18:00:00', 'Reserva creada, esperando aprobación'),

-- Reserva 14 (PENDIENTE → APROBADO)
(14, 'PENDIENTE', '2025-06-14 06:00:00', 'Reserva creada, esperando aprobación'),
(14, 'APROBADO',  '2025-06-14 07:00:00', 'Reserva aprobada por administración'),

-- Reserva 15 (PENDIENTE → RECHAZADO)
(15, 'PENDIENTE', '2025-06-15 10:00:00', 'Reserva creada, esperando aprobación'),
(15, 'RECHAZADO', '2025-06-15 11:00:00', 'Reserva rechazada por falta de cupos'),

-- Reserva 16 (PENDIENTE → CANCELADA)
(16, 'PENDIENTE','2025-06-16 12:00:00', 'Reserva creada, esperando aprobación'),
(16, 'CANCELADA','2025-06-16 13:00:00', 'Reserva cancelada por el usuario'),

-- Reserva 17 (PENDIENTE sólo)
(17, 'PENDIENTE','2025-06-17 14:00:00', 'Reserva creada, esperando aprobación'),

-- Reserva 18 (PENDIENTE → APROBADO)
(18, 'PENDIENTE','2025-06-18 16:00:00', 'Reserva creada, esperando aprobación'),
(18, 'APROBADO', '2025-06-18 17:00:00', 'Reserva aprobada por administración'),

-- Reserva 19 (PENDIENTE → RECHAZADO)
(19, 'PENDIENTE','2025-06-19 18:00:00', 'Reserva creada, esperando aprobación'),
(19, 'RECHAZADO','2025-06-19 19:00:00', 'Reserva rechazada por uso no permitido'),

-- Reserva 20 (PENDIENTE → CANCELADA)
(20, 'PENDIENTE','2025-06-20 06:00:00', 'Reserva creada, esperando aprobación'),
(20, 'CANCELADA','2025-06-20 07:00:00', 'Reserva cancelada por el usuario');

