class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/" {
            controller = "question"
            action = "index"
        }
        "404" {
            controller = "question"
            action = "index"
        }
        "500"(view:'/error')
	}
}
