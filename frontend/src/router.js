import * as Backbone from 'backbone'
import $ from "jquery";
import { IndexView, WaypointsView, CreateWaypointsView, EditWaypointsView, ToursView, CreateToursView } from './views'
import { DriverModel, WaypointCollection, WaypointModel, TourCollection  } from './model'

class Router extends Backbone.Router {

  constructor () {
    super({routes : {
      ''                   : 'index',
      'index'              : 'index',
      'waypoints'          : 'waypoints',
      'waypoints-new'      : 'createWaypoint',
      'waypoints-edit/:id' : 'editWaypoint',
      'tours'              : 'tours',
      'tours-new'          : 'createTour',
    }})    
  }


  initialize() {
    this.driver = new DriverModel();
    this.driver.fetch({
      success: () => { 
        this.waypoints = new WaypointCollection(this.driver)
        this.tours = new TourCollection(this.driver)
        Backbone.history.start() 
      }
    })    
  }

  index() {
    const view = new IndexView({model : this.driver})
    $('#app').html(view.render().$el)
  }

  waypoints() {
    const view = new WaypointsView({model : this.waypoints})

    $('#app').html(view.render().$el)
  }

  tours() {
    const view = new ToursView({model : this.tours})

    $('#app').html(view.render().$el)
  }

  createWaypoint() {
    const view = new CreateWaypointsView({model : this.waypoints})
    $('#app').html(view.render().$el)
  }

  createTour() {
    const view = new CreateToursView({tours : this.tours, waypoints : this.waypoints})
    $('#app').html(view.render().$el) 
  }

  editWaypoint(id) {
    const waypoint = new WaypointModel({ id })
    waypoint.setDriver(this.driver)
    const view = new EditWaypointsView({model : waypoint})
    $('#app').html(view.render().$el)
  }

}

export default Router