import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"



class CreateToursView extends Backbone.View {
  constructor(options) {
    super(Object.assign({}, options) )
    this.tours = options.tours;
    this.waypoints = options.waypoints;
    this.waypoints.fetch({reset: true })

    this.listenTo(this.waypoints, 'reset', this.addAllWaypoints);
  }
  initialize () {
    this.template = $('script[name="create-tour"]').html()
  }
  addAllWaypoints() {
    this.waypoints.models.forEach(model => 
      this.$waypointsList.append('<tr><td>'+model.attributes.recipient+'</td><td>'+model.attributes.address+'</td></tr>')
    ) 
  }
  render () {
    this.$el.html(_.template(this.template))
    this.$waypointsList = $('.waypoints-list', this.$el)
    this.$tourWaypointsList = $('.tour-waypoints-list', this.$el)

    return this
  }
}

class ToursView extends Backbone.View {

  constructor(options) {
    super(Object.assign({}, options) )
  }

  initialize () {
    this.template = $('script[name="tours"]').html()
    this.listenTo(this.model, 'remove', this.addAll);
    this.listenTo(this.model, 'reset', this.addAll);

    this.model.fetch({reset: true })
  }

  addOne (tour) {
    const view = new TourView({ model: tour })
    this.$toursList.append(view.render().$el)
  }

  addAll () {
      this.$toursList.html('')
      this.model.each(this.addOne, this)
  }

  render () {
    this.$el.html(_.template(this.template))
    this.$toursList = $('.tours-list', this.$el)
    return this
  }

}

class TourView extends Backbone.View {

  constructor(options) {
    super(Object.assign({}, options, {tagName : 'tr'}) )
  }

  initialize () {
    this.template = _.template($('script[name="tour"]').html())
  }

  render () {
    this.$el.html(this.template(this.model.attributes))    
    $('#edit', this.$el).click(() => {
      Backbone.history.navigate(`tours-edit/${this.model.attributes.id}`, {trigger:true})
    })
    $('#delete', this.$el).click(() => {
      const $okButton = $('#ok', '#askRemoveTour')
      const $dialog = $('#askRemoveTour')
      const okClick = this.delete.bind(this)
      $okButton.on('click', okClick)

      $dialog.modal('show')
      const cleanUp = function() {
        $dialog.off('hide.bs.modal', cleanUp)
        $okButton.off('click', okClick)
      }
      $dialog.on('hide.bs.modal', cleanUp)
    })
    return this;
  }

  delete() {
    this.model.destroy({wait: true})
  }

}



class IndexView extends Backbone.View {  

  initialize () {
    this.template = _.template($('script[name="index"]').html());
  }

  render () {
    this.$el.html(this.template(this.model.attributes))    
    return this;
  }

}

class WaypointsView extends Backbone.View {

  constructor(options) {
    super(Object.assign({}, options) )
  }

  initialize () {
    this.template = $('script[name="waypoints"]').html()
    this.listenTo(this.model, 'remove', this.addAll);
    this.listenTo(this.model, 'reset', this.addAll);

    this.model.fetch({reset: true })
  }

  addOne (waypoint) {
    const waypointView = new WaypointView({ model: waypoint })
    this.$waypointsList.append(waypointView.render().$el)
  }

  addAll () {
      this.$waypointsList.html('')
      this.model.each(this.addOne, this)
  }

  render () {
    this.$el.html(_.template(this.template))
    this.$waypointsList = $('.waypoints-list', this.$el)
    return this
  }

}

class WaypointView extends Backbone.View {

  constructor(options) {
    super(Object.assign({}, options, {tagName : 'tr'}) )
  }

  initialize () {
    this.template = _.template($('script[name="waypoint"]').html())
  }

  render () {
    this.$el.html(this.template(this.model.attributes))    
    $('#edit', this.$el).click(() => {
      Backbone.history.navigate(`waypoints-edit/${this.model.attributes.id}`, {trigger:true})
    })
    $('#delete', this.$el).click(() => {
      const $okButton = $('#ok', '#askRemoveWaypoint')
      const $dialog = $('#askRemoveWaypoint')
      const okClick = this.delete.bind(this)
      $okButton.on('click', okClick)

      $dialog.modal('show')
      const cleanUp = function() {
        $dialog.off('hide.bs.modal', cleanUp)
        $okButton.off('click', okClick)
      }
      $dialog.on('hide.bs.modal', cleanUp)
    })
    return this;
  }

  delete() {
    this.model.destroy({wait: true})
  }

}


class BaseWaypointView extends Backbone.View {

  newWaypoint () {
    return {
      recipient: $("#recipient", this.$el).val().trim(),
      address:   $("#address", this.$el).val().trim(),
      location:  this.location,
      type:      $("#type", this.$el).val().trim(),
    }
  }
  setLocation (location) {
    const sp = location.split(',')
    this.location = location
    const latLng = new google.maps.LatLng(Number(sp[0]),Number(sp[1]))
    this.map.panTo(latLng)
    this.retrieveAddress(latLng)
  }
  retrieveAddress(latLng) {
    $.get(`http://maps.googleapis.com/maps/api/geocode/json?latlng=${latLng.lat()},${latLng.lng()}&sensor=false&language=en`, function(data) {
      this.placeMarker(latLng, _.get(data, 'results[0].formatted_address'))
    }.bind(this))  
  }
  placeMarker(position, content) {
    if(this.marker) {
      this.marker.setMap(null)
    }
    this.marker = new google.maps.Marker({ position, map: this.map });
    const infowindow = new google.maps.InfoWindow({ content: content ? content : 'Unnamed waypoint' })
    if(content) {
      $("#address", this.$el).val(content)
      this.location = `${position.lat()},${position.lng()}`
    }
    infowindow.open(this.map, this.marker)
  }
  renderMap() {    
      const mapCanvas = $("#map", this.$el).get(0)
      const myCenter=new google.maps.LatLng(38.67178737149994, -101.83447480201721)  
      const mapOptions = {center: myCenter, zoom: 4, mapTypeId: google.maps.MapTypeId.ROADMAP}      
      this.map = new google.maps.Map(mapCanvas, mapOptions)
      google.maps.event.addListener(this.map, 'click', event => this.retrieveAddress(event.latLng))
  }
  
}

class CreateWaypointsView extends BaseWaypointView {

  initialize () {
    this.listenTo(this.model, 'add', ()=> {
      Backbone.history.navigate('waypoints', {trigger:true})
    });
    this.template = $('script[name="create-waypoint"]').html()
  }

  render () {
    this.$el.html(_.template(this.template))
    this.renderMap()
    $("#submit", this.$el).click(() => this.model.create(this.newWaypoint(), {wait: true}))
    $("#cancel", this.$el).click(() => Backbone.history.navigate('waypoints', {trigger:true}))
    return this
  }

}

class EditWaypointsView extends BaseWaypointView {

  initialize () {
    this.listenTo(this.model, 'change', this.setWaypoint)

    this.template = $('script[name="create-waypoint"]').html()
    this.model.fetch()
  }

  setWaypoint () {
    $("#recipient", this.$el).val(this.model.attributes.recipient)
    $("#address", this.$el).val(this.model.attributes.address)
    $("#type", this.$el).val(this.model.attributes.type)
    this.setLocation(this.model.attributes.location)
  }  

  render () {
    this.$el.html(_.template(this.template))
    this.renderMap()
    $("#submit", this.$el).click(() => this.model.save(this.newWaypoint(), {
      wait: true, 
      success: () => Backbone.history.navigate('waypoints', {trigger:true}) 
    }))
    $("#cancel", this.$el).click(() => Backbone.history.navigate('waypoints', {trigger:true}))
    return this
  }

}

export { IndexView, WaypointsView, CreateWaypointsView, EditWaypointsView, ToursView, CreateToursView }