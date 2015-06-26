require 'sinatra'

set :views, settings.root
set :public_folder, File.dirname(__FILE__) + '/build'

helpers do
  def include_svg(file)
    begin
      File.read("#{File.dirname(__FILE__)}/src/svg/#{file}.svg")
    rescue Errno::ENOENT => e
      File.read("#{File.dirname(__FILE__)}/src/svg/news-base-layout/#{file}.svg")
    end
  end
end

get '/' do
  erb :index
end
