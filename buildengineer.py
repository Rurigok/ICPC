
class Project(object):

    dependencies = []
    is_built = False

    def __init__(self, name):
        self.name = name

    def add_dependency(self, dependency):
        self.dependencies.append(dependency)

def dfs(visited_path, project_node, output_list):
    if len(project_node.dependencies) <= 0:
        output_list.append()
        project_nodeis_built = True
        return
    visited_path.append(project_node)
    for node in project_node.dependencies:
        if node not in visited_path:
            dfs(visited_path, node)
        else: # cyclic dependency
            pass
    visited_path.pop()


num_products = int(input())

for i in range(num_products): # for each product
    num_projects = int(input())
    num_builds = int(input())
    projects = {}
    for j in range(num_projects): # for each project description
        proj_desc = input().split(" ")
        num_depend = int(proj_desc[0])
        project = Project(proj_desc[1])
        projects[project.name] = project
        for k in range(num_depend): # for each dependency line
            depend_name = input()
            if depend_name not in projects:
                dproject = Project(depend_name)
                project.add_dependency(dproject)
                projects[depend_name] = dproject
            else:
                project.add_dependency(projects[depend_name])
            print("{} now depends on {}".format(project.name, depend_name))
    for j in range(num_builds): # for each build line
        build_name = input().split()[1]
        visited = []
        dfs(visited, projects[build_name])
        for v in visited[::-1]:
            print(v.name)
        print("Build {}".format(build_name))
    print()
