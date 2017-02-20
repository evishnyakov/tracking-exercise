const BASE_URL = 'http://localhost:8080/'

export const DRIVER_URL = BASE_URL + 'drivers'

export const WAYPOINTS_URL = driverId => `${DRIVER_URL}/${driverId}/waypoints/`

export const TOURS_URL =  driverId => `${DRIVER_URL}/${driverId}/tours/`