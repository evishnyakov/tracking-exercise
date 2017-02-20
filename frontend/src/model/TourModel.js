import * as Backbone from 'backbone'
import $ from "jquery"
import _ from "lodash"
import { TOURS_URL } from '../constants'

export default class TourModel extends Backbone.Model {

    constructor() {
        super()
		this.idAttribute= "id"
        Backbone.Model.apply(this, arguments)
    }

    setDriver(driver) {
		this.urlRoot = TOURS_URL(driver.id)
    }

}