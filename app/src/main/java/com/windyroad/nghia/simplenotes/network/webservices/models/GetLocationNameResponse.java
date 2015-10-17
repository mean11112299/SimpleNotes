package com.windyroad.nghia.simplenotes.network.webservices.models;

import android.view.View;

import java.util.List;

/**
 * Created by Nghia on 11-10-15.
 * Lấy giá trị trả về của Google Geo
 */
public class GetLocationNameResponse {
    public static final String STATUS_OK = "OK";

    private List<Result> results;
    private String status;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public class Result {
        private List<AddressComponents> address_components;
        private String formatted_address;
        private String place_id;
        private List<String> types;
        private Geometry geometry;

        public List<AddressComponents> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponents> address_components) {
            this.address_components = address_components;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }


        public class AddressComponents {
            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }

        public class Geometry {

            private String location_type;
            private Bounds bounds;
            private Location location;
            private Viewport viewport;

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public Bounds getBounds() {
                return bounds;
            }

            public void setBounds(Bounds bounds) {
                this.bounds = bounds;
            }

            public Location getLocation() {
                return location;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public Viewport getViewport() {
                return viewport;
            }

            public void setViewport(Viewport viewport) {
                this.viewport = viewport;
            }


            public class Bounds {
                private Location northeast;
                private Location southwest;

                public Location getNortheast() {
                    return northeast;
                }

                public void setNortheast(Location northeast) {
                    this.northeast = northeast;
                }

                public Location getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(Location southwest) {
                    this.southwest = southwest;
                }
            }

            public class Viewport {
                private Location northeast;
                private Location southwest;

                public Location getNortheast() {
                    return northeast;
                }

                public void setNortheast(Location northeast) {
                    this.northeast = northeast;
                }

                public Location getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(Location southwest) {
                    this.southwest = southwest;
                }
            }

            public class Location {
                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

        }
    }
}
