import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"
import { WAYPOINTS_URL } from '../constants'

export default class WaypointModel extends Backbone.Model {

    constructor() {
        super()
		this.idAttribute= "id"
        Backbone.Model.apply(this, arguments)
    }

    setDriver(driver) {
		this.urlRoot = WAYPOINTS_URL(driver.id)
    }

}