import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"
import { DRIVER_URL } from '../constants'

export default class DriverModel extends Backbone.Model {

	constructor() {
		super()
		this.urlRoot = DRIVER_URL
	}

}