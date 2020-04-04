package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ArmorServlets extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <!-- Required meta tags -->\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "    <!-- Bootstrap CSS -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"../style/editor.css\">\n" +
                "    <title>Editor</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"row\" >\n" +
                "    <!-- Боковая панель -->\n" +
                "    <div class=\"col-3 pr-0 d-sm-none d-lg-block\"  style=\"height: 100vh;\">\n" +
                "        <div class=\"nav flex-column \" id=\"v-pills-tab\" role=\"tablist\" aria-orientation=\"vertical\">\n" +
                "            <ul class=\"nav flex-column\" >\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" href=\"../../src/main/webapp/index.html\">На главную</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link active\" id=\"class-tab\" data-toggle=\"tab\" href=\"#class\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icons.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Класс\n" +
                "                        </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"race-tab\" data-toggle=\"tab\" href=\"#race\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icon-race.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Расa\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"backstage-tab\" data-toggle=\"tab\" href=\"#backstage\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icon-backstage.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Предыстория\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"feats-tab\" data-toggle=\"tab\" href=\"#feats\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icons.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Черта\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"weapon-tab\" data-toggle=\"tab\" href=\"#weapon\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                       <img src=\"../resources/icon-weapon.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Оружие\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"armor-tab\" data-toggle=\"tab\" href=\"#armor\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icon-armor.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Броня\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"items-tab\" data-toggle=\"tab\" href=\"#items\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icon-item.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Предметы\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"skills-tab\" data-toggle=\"tab\" href=\"#skills\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                       <img src=\"../resources/icon-skill.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Навыки\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a class=\"nav-link \" id=\"magic-tab\" data-toggle=\"tab\" href=\"#magic\"\n" +
                "                       role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">\n" +
                "                        <img src=\"../resources/icon-magic.png\" alt=\"icons\" height=\"30px\" width=\"30px\">\n" +
                "                        Магия\n" +
                "                    </a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <!-- Контент -->\n" +
                "    <div class=\"col-9 pl-0 themecolor\">\n" +
                "        <div class=\"tab-content m-5\" id=\"v-pills-tabContent\">\n" +
                "            <!-- Классы -->\n" +
                "            <div class=\"tab-pane fade show active\" id=\"class\" role=\"tabpanel\" aria-labelledby=\"class-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Классы</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Варвар</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <hr/>\n" +
                "                <h5>Новый класс</h5>\n" +
                "                <form>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"className\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"className\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"classDescription\">Описание класса</label>\n" +
                "                        <textarea class=\"form-control\" id=\"classDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <h4>Список навыков</h4>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Расы -->\n" +
                "            <div class=\"tab-pane fade\" id=\"race\" role=\"tabpanel\" aria-labelledby=\"race-tab\">\n" +
                "                <!-- Список -->\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Расы</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Драконорожденный</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <hr/>\n" +
                "                <h5>Новая раса</h5>\n" +
                "                <form>\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"raceName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"raceName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"raceDescription\">Описание расы</label>\n" +
                "                        <textarea class=\"form-control\" id=\"raceDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <div class=\"row\">\n" +
                "                        <!-- Размер -->\n" +
                "                        <div class=\"form-group row col-6\">\n" +
                "                            <label class=\"col-sm-7 col-form-label\" for=\"size\">Размер</label>\n" +
                "                            <div class=\"col-sm-5\">\n" +
                "                                <select class=\"form-control\" id=\"size\">\n" +
                "                                    <option>S</option>\n" +
                "                                    <option>M</option>\n" +
                "                                    <option>L</option>\n" +
                "                                    <option>XL</option>\n" +
                "                                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <!-- Скорость -->\n" +
                "                        <div class=\"form-group row col-6\">\n" +
                "                            <label class=\"col-sm-7 col-form-label\" for=\"speed\">Скорость</label>\n" +
                "                            <div class=\"col-sm-5\">\n" +
                "                                <select class=\"form-control\" id=\"speed\">\n" +
                "                                    <option>25</option>\n" +
                "                                    <option>30</option>\n" +
                "                                    <option>35</option>\n" +
                "                                </select>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Бонусы к характеристикам -->\n" +
                "                    <div>\n" +
                "                        <h6>Бонусы к характеристикам</h6>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"strength\">Сила</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"strength\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"dexterity\">Ловкость</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"dexterity\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"constitution\">Телосложение</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"constitution\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"intelligence\">Интеллект</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"intelligence\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"wisdom\">Мудрость</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"wisdom\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"form-group row\">\n" +
                "                            <label class=\"col-sm-9 col-form-label\" for=\"charisma\">Харизма</label>\n" +
                "                            <div class=\"col-sm-3\">\n" +
                "                                <input value=\"0\" class=\"form-control\" id=\"charisma\" placeholder=\"Введите количество\"/>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <h4>Список навыков</h4>\n" +
                "                    <h4>Список владений</h4>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Предыстория -->\n" +
                "            <div class=\"tab-pane fade\" id=\"backstage\" role=\"tabpanel\" aria-labelledby=\"backstage-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Предыстории</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Отшельник</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form>\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"backstageName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"backstageName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"backstageDescription\">Описание предыстории</label>\n" +
                "                        <textarea class=\"form-control\" id=\"backstageDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <h4>Список навыков</h4>\n" +
                "                    <h4>Список владений и инструментов</h4>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Черты -->\n" +
                "            <div class=\"tab-pane fade\" id=\"feats\" role=\"tabpanel\" aria-labelledby=\"feats-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Черты</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Грациозный</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form>\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"featsName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"featsName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"featsDescription\">Описание черты</label>\n" +
                "                        <textarea class=\"form-control\" id=\"featsDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <h4>Список требований</h4>\n" +
                "                    <h4>Список бонусов</h4>\n" +
                "                </form>\n" +
                "                <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "            </div>\n" +
                "            <!-- Оружие -->\n" +
                "            <div class=\"tab-pane fade\" id=\"weapon\" role=\"tabpanel\" aria-labelledby=\"weapon-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Оружия</th>\n" +
                "                        <th>Урон</th>\n" +
                "                        <th>Тип</th>\n" +
                "                        <th>Вес</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Рапира</td>\n" +
                "                        <td>1k8</td>\n" +
                "                        <td>Колющее</td>\n" +
                "                        <td>3</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form method=\"post\" action=\"weapon\">\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"weaponName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"weaponName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"weaponDescription\">Описание класса</label>\n" +
                "                        <textarea class=\"form-control\" id=\"weaponDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <!-- Категории оружия -->\n" +
                "                    <div class=\"weapon-category container row\">\n" +
                "                        <h4 class=\"mr-4\">Категория:</h4>\n" +
                "                        <div class=\"ml-2 mt-1 col\">\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"radio\" class=\"custom-control-input\" id=\"simpleWeapon\" name=\"SMWeapon\" checked>\n" +
                "                                <label class=\"custom-control-label\" for=\"simpleWeapon\">Простое</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"radio\" class=\"custom-control-input\" id=\"militaryWeapon\" name=\"SMWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"militaryWeapon\">Воиское</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"rangerWeapon\" name=\"rangerWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"rangerWeapon\">Дальнобойное</label>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Свойства -->\n" +
                "                    <h4 class=\"mr-4\">Свойства:</h4>\n" +
                "                    <div class=\"properties container ml-3 row\">\n" +
                "                        <div class=\"ml-2 mt-1 col\">\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"ammoWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"ammoWeapon\">Боеприпас</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"TwoHandWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"TwoHandWeapon\">Двуручное</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"reachWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"reachWeapon\">Досягаемость</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"distanceWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"distanceWeapon\">Дистанция</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"lightWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"lightWeapon\">Легкое</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"throwableWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"throwableWeapon\">Метательное</label>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class=\"ml-2 mt-2 col\">\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"specialWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"specialWeapon\">Особое</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"reloadWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"reloadWeapon\">Перезарядка</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"heavyWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"heavyWeapon\">Тяжелое</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"universalWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"universalWeapon\">Универсальное</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"fencingWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"fencingWeapon\">Фехтовальное</label>\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group row\">\n" +
                "                                <input type=\"checkbox\" class=\"custom-control-input\" id=\"hindranceWeapon\">\n" +
                "                                <label class=\"custom-control-label\" for=\"hindranceWeapon\">Помеха</label>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Стоимость оружия -->\n" +
                "                    <div class=\"form-group row ml-2\">\n" +
                "                        <label class=\"col-5\" for=\"costWeapon\">Стоимость</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input type=\"text\" class=\"form-control\" id=\"costWeapon\" placeholder=\"Стоимость\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Урон оружия -->\n" +
                "                    <div class=\"form-group row ml-2\">\n" +
                "                        <label class=\"col-5\" for=\"damageWeapon\">Урон</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input type=\"text\" class=\"form-control \" id=\"damageWeapon\" placeholder=\"1k12\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Вес оружия -->\n" +
                "                    <div class=\"form-group row ml-2\">\n" +
                "                        <label class=\"col-5\" for=\"weightWeapon\">Вес</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input type=\"text\" class=\"form-control\" id=\"weightWeapon\" placeholder=\"Вес\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Броня -->\n" +
                "            <div class=\"tab-pane fade\" id=\"armor\" role=\"tabpanel\" aria-labelledby=\"armor-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Броня</th>\n" +
                "                        <th>Класс брони</th>\n" +
                "                        <th>Цена</th>\n" +
                "                    </tr>\n");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dnd",
                    "postgres", "9742");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ArmorId, ArmorName, ArmorTypeName, ArmorPrice " +
                    "FROM Armor INNER JOIN ArmorType ON (Armor.ArmorType = ArmorType.ArmorTypeId)");
            while(resultSet.next()) {
                printWriter.println(
                        "<tr>" +
                                "<td>" + resultSet.getString("ArmorId") + "</td>" +
                                "<td>" + resultSet.getString("ArmorName") + "</td>" +
                                "<td>" + resultSet.getString("ArmorTypeName") + "</td>" +
                                "<td>" + resultSet.getString("ArmorPrice") + "</td>" + "</tr>"
                );
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        printWriter.println("</table> <form action=\"armor\" method=\"post\">\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"armorName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"armorName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"armorDescription\">Описание брони</label>\n" +
                "                        <textarea class=\"form-control\" id=\"armorDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <!-- Тип доспеха -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \" for=\"armorType\">Тип доспеха</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <select id=\"armorType\" class=\"custom-select\">\n" +
                "                                <option value=\"light\" selected>Легкие</option>\n" +
                "                                <option value=\"middle\">Средние</option>\n" +
                "                                <option value=\"heavy\">Тяжелые</option>\n" +
                "                            </select>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Класс доспеха (КД) -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \"  for=\"armorClass\">Класс доспеха</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"armorClass\" placeholder=\"Класс доспеха\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Требования к силе -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \"  for=\"armorStrength\">Необходимая сила</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"armorStrength\" placeholder=\"Сила\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Штраф к скрытности -->\n" +
                "                    <div class=\"form-group row ml-1 container\">\n" +
                "                        <input type=\"checkbox\" class=\"custom-control-input\" id=\"hindranceStealth\">\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <label class=\"custom-control-label\" for=\"hindranceStealth\">Помеха скрытности</label>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Стоимость -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \"  for=\"armorCost\">Цена</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"armorCost\" placeholder=\"Цена\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Вес -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9\" for=\"armorWeight\">Вес</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"armorWeight\" placeholder=\"Вес\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Предметы -->\n" +
                "            <div class=\"tab-pane fade\" id=\"items\" role=\"tabpanel\" aria-labelledby=\"items-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Предметы</th>\n" +
                "                        <th>Описание</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Целебное зелье</td>\n" +
                "                        <td>Восстанавливает 2к4+4 единиц здоровья</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form action=\"items\" method=\"post\">\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"itemsName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"itemsName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"itemsDescription\">Описание класса</label>\n" +
                "                        <textarea class=\"form-control\" id=\"itemsDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \"  for=\"itemsCost\">Цена</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"itemsCost\" placeholder=\"Цена\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <!-- Вес -->\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 \"  for=\"itemsWeight\">Вес</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input class=\"form-control\" id=\"itemsWeight\" placeholder=\"Вес\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Скиллы -->\n" +
                "            <div class=\"tab-pane fade\" id=\"skills\" role=\"tabpanel\" aria-labelledby=\"skills-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Навыки</th>\n" +
                "                        <th>Описание</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Ночное зрение</td>\n" +
                "                        <td>Позволяет видеть в темноте в радиусе 60 футов</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form>\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"skillsName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"skillsName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"skillsDescription\">Описание навыка</label>\n" +
                "                        <textarea class=\"form-control\" id=\"skillsDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "            <!-- Магия -->\n" +
                "            <div class=\"tab-pane fade\" id=\"magic\" role=\"tabpanel\" aria-labelledby=\"magic-tab\">\n" +
                "                <table class=\"table\">\n" +
                "                    <tr>\n" +
                "                        <th>#</th>\n" +
                "                        <th>Заклинания</th>\n" +
                "                        <th>Школа</th>\n" +
                "                        <th>Описание</th>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>1</td>\n" +
                "                        <td>Исцеляющие руки</td>\n" +
                "                        <td>Восстановление</td>\n" +
                "                        <td>Исцеляет прикосновением на 1к4 единиц здоровья</td>\n" +
                "                        <td>Изменить</td>\n" +
                "                        <td>Удалить</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <form>\n" +
                "                    <!-- Название -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"magicName\">Название</label>\n" +
                "                        <input class=\"form-control\" id=\"magicName\" placeholder=\"Введите название\"/>\n" +
                "                    </div>\n" +
                "                    <!-- Описание -->\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"magicDescription\">Описание заклинания</label>\n" +
                "                        <textarea class=\"form-control\" id=\"magicDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label for=\"magicHLDescription\">На больших уровнях</label>\n" +
                "                        <textarea class=\"form-control\" id=\"magicHLDescription\" placeholder=\"Введите описание\"></textarea>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9  col-form-label\" for=\"magicLevel\">Уровень</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicLevel\" placeholder=\"Уровень\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9  col-form-label\" for=\"magicSchool\">Школа</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicSchool\" placeholder=\"Школа\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9  col-form-label\" for=\"magicCastingTime\">Время накладывания</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicCastingTime\" placeholder=\"Время накладывания\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 col-form-label\" for=\"magicTarget\">Цель</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicTarget\" placeholder=\"Время накладывания\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9 col-form-label\" for=\"magicDistance\">Дистанция в футах</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicDistance\" placeholder=\"Дистанция\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9  col-form-label\" for=\"magicComponent\">Компоненты</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicComponent\" placeholder=\"Время накладывания\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group  row\">\n" +
                "                        <label class=\"col-sm-9 col-form-label\" for=\"magicDuration\">Длительность</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicDuration\" placeholder=\"Длительность\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group row\">\n" +
                "                        <label class=\"col-sm-9  col-form-label\" for=\"magicRitual\">Ритуал</label>\n" +
                "                        <div class=\"col-sm-3\">\n" +
                "                            <input value=\"0\" class=\"form-control\" id=\"magicRitual\" placeholder=\"Время накладывания\"/>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <button type=\"submit\" class=\"btn btn-block btn-secondary\">Добавить</button>\n" +
                "                </form>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\"\n" +
                "        integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\">\n" +
                "</script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"\n" +
                "        integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\">\n" +
                "</script>\n" +
                "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"\n" +
                "        integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\">\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        printWriter.close();
    }
}
