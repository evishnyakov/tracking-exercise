import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"
import WaypointModel from './WaypointModel'
import { WAYPOINTS_URL } from '../constants'

export default class WaypointCollection extends Backbone.Collection {

	constructor(driver) {
		super()
		this.model = WaypointModel
		this.url = WAYPOINTS_URL(driver.id)
	}
	
}