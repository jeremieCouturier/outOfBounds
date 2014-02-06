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

        name about: "/about"(view: "about")

        "404" {
            controller = "question"
            action = "index"
        }
        "500"(view:'/error')
	}
}
