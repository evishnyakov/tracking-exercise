import $ from "jquery"
import Router from './router'
import * as Backbone from 'backbone'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'

$(() => {

   new Router().on("route", function(route, params) {
        const menus = $('#menu li')
        let activeMenu;
        menus.each(function () {
          const href = $('a', this).first().attr('href').substring(1)
          if(route.includes(href)) {
            activeMenu = $(this)
          }
        })
        if(activeMenu) {
          menus.removeClass('active')
          activeMenu.addClass('active') 
      }
   });
});
