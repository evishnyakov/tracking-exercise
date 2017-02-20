import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"
import TourModel from './TourModel'
import { TOURS_URL } from '../constants'

export default class TourCollection extends Backbone.Collection {

	constructor(driver) {
		super()
		this.model = TourModel
		this.url = TOURS_URL(driver.id)
	}
	
}